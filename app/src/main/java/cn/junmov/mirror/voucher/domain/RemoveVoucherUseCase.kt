package cn.junmov.mirror.voucher.domain

import cn.junmov.mirror.core.data.db.dao.AccountDao
import cn.junmov.mirror.core.data.db.dao.VoucherDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.time.LocalDateTime

class RemoveVoucherUseCase(private val voucherDao: VoucherDao, private val accountDao: AccountDao) {

    suspend operator fun invoke(voucherId: Long) {
        val now = LocalDateTime.now()

        val voucher = voucherDao.findById(voucherId)
        val amount = voucher.amount
        val debit = accountDao.findById(voucher.debitId)
        val credit = accountDao.findById(voucher.creditId)
        debit.plusAmount(true, -amount)
        debit.modifiedAt = now
        credit.plusAmount(false, -amount)
        credit.modifiedAt = now
        voucher.deleted = true
        voucher.modifiedAt = now
        withContext(Dispatchers.IO) {
            voucherDao.submitVoucherTransaction(voucher, debit, credit, false)
        }
    }
}