package cn.junmov.mirror.voucher.domain

import cn.junmov.mirror.core.data.db.dao.AccountDao
import cn.junmov.mirror.core.data.db.dao.VoucherDao
import cn.junmov.mirror.core.data.db.entity.Split
import cn.junmov.mirror.core.data.db.entity.Voucher
import java.time.LocalDateTime

class RemoveVoucherUseCase(private val voucherDao: VoucherDao, private val accountDao: AccountDao) {

    suspend operator fun invoke(voucher: Voucher, splits: List<Split>) {
        val now = LocalDateTime.now()
        val accountIds = if (voucher.audited) {
            analysisAccount(splits)
        } else {
            emptyList()
        }
        val accounts = accountDao.listAllById(accountIds)
        splits.forEach {
            it.modifiedAt = now
            it.deleted = true
            var first = false
            var second = it.accountParentId == 0L
            for (account in accounts) {
                if (first && second) break
                if (!first && it.accountId == account.id) {
                    account.plusAmount(it.debit, -it.amount)
                    first = true
                }
                if (!second && it.accountParentId == account.id) {
                    account.plusAmount(it.debit, -it.amount)
                    second = true
                }
            }
        }
        accounts.forEach {
            it.modifiedAt = now
            it.tradeCount--
        }
        voucher.modifiedAt = now
        voucher.deleted = true
        voucherDao.removeAuditedVoucherTransaction(voucher, splits, accounts)
    }

    private fun analysisAccount(splits: List<Split>): List<Long> {
        val accountIds = mutableListOf<Long>()
        for (s in splits) {
            accountIds.add(s.accountId)
            if (s.accountParentId != 0L) accountIds.add(s.accountParentId)
        }
        return accountIds.distinct()
    }

}