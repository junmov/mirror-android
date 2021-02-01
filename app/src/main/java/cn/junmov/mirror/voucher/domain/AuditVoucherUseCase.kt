package cn.junmov.mirror.voucher.domain

import cn.junmov.mirror.core.data.db.dao.AccountDao
import cn.junmov.mirror.core.data.db.dao.AuditDao
import cn.junmov.mirror.core.data.db.entity.Account
import cn.junmov.mirror.core.data.db.entity.Split
import cn.junmov.mirror.core.data.db.entity.Voucher
import cn.junmov.mirror.core.data.model.VoucherType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.time.LocalDateTime

class AuditVoucherUseCase(private val dao: AuditDao, private val accountDao: AccountDao) {

    suspend operator fun invoke(voucher: Voucher, splits: List<Split>) {
        val now = LocalDateTime.now()
        val accountIds = analysisAccount(splits)
        val accounts = accountDao.listAllById(accountIds)

        var profit = 0
        splits.forEach { split ->
            val delta = split.balanceDelta()
            when {
                split.accountType.isIncome() -> {
                    profit += delta
                }
                split.accountType.isExpend() -> {
                    profit -= delta
                }
                else -> {
                }
            }
            var first = false
            var second = split.accountParentId == 0L
            for (account in accounts) {
                if (first && second) break
                if (!first && split.accountId == account.id) {
                    account.plusAmount(split.debit, split.amount)
                    first = true
                }
                if (!second && split.accountParentId == account.id) {
                    account.plusAmount(split.debit, split.amount)
                    second = true
                }
            }
        }
        accounts.forEach { account ->
            account.modifiedAt = now
            account.tradeCount++
        }
        voucher.profit = profit
        voucher.type = when {
            profit > 0 -> VoucherType.INCOME
            profit < 0 -> VoucherType.EXPEND
            else -> VoucherType.TRANSFER
        }
        voucher.audited = true
        voucher.modifiedAt = now
        applyChange(voucher, accounts)
    }

    private fun analysisAccount(splits: List<Split>): List<Long> {
        val accountIds = mutableListOf<Long>()
        for (s in splits) {
            accountIds.add(s.accountId)
            if (s.accountParentId != 0L) accountIds.add(s.accountParentId)
        }
        return accountIds.distinct()
    }

    private suspend fun applyChange(voucher: Voucher, accounts: List<Account>) {
        withContext(Dispatchers.IO) {
            dao.auditTransaction(voucher, accounts)
        }
    }

}