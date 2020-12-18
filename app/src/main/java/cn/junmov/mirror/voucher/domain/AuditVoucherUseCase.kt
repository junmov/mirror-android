package cn.junmov.mirror.voucher.domain

import cn.junmov.mirror.core.data.dao.AccountDao
import cn.junmov.mirror.core.data.dao.AuditDao
import cn.junmov.mirror.core.data.entity.Account
import cn.junmov.mirror.core.data.entity.Split
import cn.junmov.mirror.core.data.entity.Trade
import cn.junmov.mirror.core.data.entity.Voucher
import cn.junmov.mirror.core.utility.SnowFlakeUtil
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.time.LocalDateTime

class AuditVoucherUseCase(private val dao: AuditDao, private val accountDao: AccountDao) {

    suspend operator fun invoke(voucher: Voucher, splits: List<Split>) {
        val now = LocalDateTime.now()
        val trades = mutableListOf<Trade>()
        val accountIds = analysisAccount(splits)
        val accounts = accountDao.listAllById(accountIds)

        val map = accountIds.associateWith { 0 }.toMutableMap()
        splits.forEach { split ->
            val delta = split.balanceDelta()
            if (split.accountType.isIncome()) {
                voucher.profit += delta
            } else if (split.accountType.isExpend()) {
                voucher.profit -= delta
            }
            var first = false
            var second = split.accountParentId == 0L
            for (entry in map.entries) {
                if (first && second) break
                if (!first && split.accountId == entry.key) {
                    entry.setValue(entry.value + delta)
                    first = true
                }
                if (!second && split.accountParentId == entry.key) {
                    entry.setValue(entry.value + delta)
                    second = true
                }
            }
        }
        val ids = SnowFlakeUtil.genIds(accounts.size)
        accounts.forEachIndexed { index, account ->
            for (entry in map.entries) {
                if (entry.key == account.id) {
                    val trade = Trade(
                        id = ids[index], voucherId = voucher.id, accountId = account.id,
                        accountType = account.type, amount = entry.value, dateAt = voucher.dateAt,
                        createAt = now, modifiedAt = now, deleted = false
                    )
                    trades.add(trade)
                    account.modifiedAt = now
                    account.tradeCount++
                    account.plusAmount(entry.value)
                    break
                }
            }
        }
        voucher.audited = true
        voucher.modifiedAt = now
        applyChange(voucher, accounts, trades)
    }

    private fun analysisAccount(splits: List<Split>): List<Long> {
        val accountIds = mutableListOf<Long>()
        for (s in splits) {
            accountIds.add(s.accountId)
            if (s.accountParentId != 0L) accountIds.add(s.accountParentId)
        }
        return accountIds.distinct()
    }

    private suspend fun applyChange(
        voucher: Voucher, accounts: List<Account>, trades: List<Trade>
    ) {
        withContext(Dispatchers.IO) {
            dao.auditTransaction(voucher, accounts, trades)
        }
    }

}