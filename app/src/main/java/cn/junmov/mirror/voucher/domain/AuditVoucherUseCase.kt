package cn.junmov.mirror.voucher.domain

import cn.junmov.mirror.core.data.AccountType
import cn.junmov.mirror.core.data.dao.AccountDao
import cn.junmov.mirror.core.data.dao.AuditDao
import cn.junmov.mirror.core.data.dao.BudgetDao
import cn.junmov.mirror.core.data.entity.*
import cn.junmov.mirror.core.utility.MoneyUtils
import cn.junmov.mirror.core.utility.SnowFlakeUtil
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.time.LocalDateTime
import java.time.YearMonth

class AuditVoucherUseCase(
    private val dao: AuditDao, private val accountDao: AccountDao, private val budgetDao: BudgetDao
) {

    suspend operator fun invoke(voucher: Voucher, splits: List<Split>) {
        val now = LocalDateTime.now()
        val voucherMonth = YearMonth.from(voucher.dateAt)
        val accounts = mutableListOf<Account>()
        val budgets = mutableListOf<Budget>()
        val trades = mutableListOf<Trade>()

        val childrenAccountIds = splits.map { it.accountId }.distinct()
        val childrenAccount = getAccount(childrenAccountIds)
        val parentAccount = getParentAccount(childrenAccount)

        val secondaryBudgets = getBudget(childrenAccount, voucherMonth)
        val firstBudgets = getBudget(parentAccount, voucherMonth)

        val childrenAccountBalanceDelta = computeBalanceDelta(childrenAccountIds, splits)
        withContext(Dispatchers.IO) {
            val ids = SnowFlakeUtil.genIds(childrenAccountIds.size)
            var idIndex = 0
            for (entry in childrenAccountBalanceDelta.entries) {
                for (account in childrenAccount) {
                    if (entry.key != account.id) continue
                    if (account.type.isIncome()) {
                        voucher.profit += entry.value
                    } else if (account.type.isExpend()) {
                        voucher.profit -= entry.value
                    }
                    account.balance += entry.value
                    account.sortKey += 1
                    for (parent in parentAccount) {
                        if (account.isSonOf(parent)) {
                            parent.balance += entry.value
                        }
                    }
                    val trade = Trade(
                        id = ids[idIndex], amount = entry.value,
                        accountId = entry.key, accountType = account.type, voucherId = voucher.id,
                        dateAt = voucher.dateAt, createAt = now, modifiedAt = now
                    )
                    trades.add(trade)
                    idIndex++
                }
                for (budget in secondaryBudgets) {
                    if (entry.key != budget.accountId) continue
                    budget.used += entry.value
                    for (parent in firstBudgets) {
                        if (budget.isSonOf(parent)) {
                            parent.used += entry.value
                        }
                    }
                }
            }
        }
        budgets.addAll(secondaryBudgets)
        budgets.addAll(firstBudgets)
        budgets.forEach { it.modifiedAt = now }
        accounts.addAll(childrenAccount)
        accounts.addAll(parentAccount)
        accounts.forEach { it.modifiedAt = now }
        voucher.modifiedAt = now
        applyChange(voucher, splits, accounts, trades, budgets)
    }

    private suspend fun computeBalanceDelta(ids: List<Long>, splits: List<Split>): Map<Long, Int> {
        val map = ids.associateWith { 0 }.toMutableMap()
        withContext(Dispatchers.Default) {
            splits.forEach { split ->
                val balanceDelta =
                    MoneyUtils.computeBalanceDelta(
                        split.accountType, split.isDebit, split.amount
                    )
                for (entry in map.entries) {
                    if (entry.key == split.accountId) {
                        entry.setValue(entry.value + balanceDelta)
                        break
                    }
                }
            }
        }
        return map
    }

    private suspend fun getAccount(ids: List<Long>): List<Account> {
        return accountDao.findAllById(ids)
    }

    private suspend fun getParentAccount(childrenAccount: List<Account>): List<Account> {
        val ids = childrenAccount.map { it.parentId }.distinct()
        return accountDao.findAllById(ids)
    }

    private suspend fun getBudget(accounts: List<Account>, month: YearMonth): List<Budget> {
        val accountIds = accounts.filter {
            it.type == AccountType.CONSUME || it.type == AccountType.EXPENSE
        }.map { it.id }
        return budgetDao.findAllByAccountId(accountIds, month)
    }

    private suspend fun applyChange(
        voucher: Voucher, splits: List<Split>,
        accounts: List<Account>, trades: List<Trade>, budgets: List<Budget>
    ) {
        withContext(Dispatchers.IO) {
            dao.auditTransaction(voucher, splits, accounts, trades, budgets)
        }
    }

}