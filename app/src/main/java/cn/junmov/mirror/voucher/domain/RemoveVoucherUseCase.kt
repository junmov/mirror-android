package cn.junmov.mirror.voucher.domain

import cn.junmov.mirror.core.data.db.dao.AccountDao
import cn.junmov.mirror.core.data.db.dao.TradeDao
import cn.junmov.mirror.core.data.db.dao.VoucherDao
import cn.junmov.mirror.core.data.db.entity.Split
import cn.junmov.mirror.core.data.db.entity.Voucher
import java.time.LocalDateTime

class RemoveVoucherUseCase(
    private val voucherDao: VoucherDao,
    private val accountDao: AccountDao,
    private val tradeDao: TradeDao,
) {

    suspend operator fun invoke(voucher: Voucher, splits: List<Split>) {
        val now = LocalDateTime.now()
        voucher.modifiedAt = now
        voucher.deleted = true
        splits.forEach {
            it.modifiedAt = now
            it.deleted = true
        }
        val trades = tradeDao.findAllByVoucher(voucher.id)
        val ids = trades.map { it.accountId }
        val accounts = accountDao.listAllById(ids)
        for (a in accounts) {
            for (t in trades) {
                if (a.id == t.accountId) {
                    a.modifiedAt = now
                    a.minusAmount(t.amount)
                    t.modifiedAt = now
                    t.deleted = true
                    break
                }
            }
        }
        voucherDao.removeAuditedVoucherTransaction(voucher, splits, trades, accounts)
    }

}