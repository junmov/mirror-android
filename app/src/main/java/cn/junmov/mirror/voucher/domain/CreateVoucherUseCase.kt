package cn.junmov.mirror.voucher.domain

import cn.junmov.mirror.core.data.db.dao.AccountDao
import cn.junmov.mirror.core.data.db.dao.VoucherDao
import cn.junmov.mirror.core.data.db.entity.Voucher
import cn.junmov.mirror.core.utility.MoneyUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.time.LocalDateTime

class CreateVoucherUseCase(private val dao: VoucherDao, private val accountDao: AccountDao) {

    suspend operator fun invoke(voucher: Voucher) {
        val now = LocalDateTime.now()
        val amount = voucher.amount
        val debit = accountDao.findById(voucher.debitId)
        val credit = accountDao.findById(voucher.creditId)
        if (now.year == voucher.dateAt.year && now.monthValue == voucher.dateAt.monthValue) {
            debit.plusAmount(true, amount)
            credit.plusAmount(false, amount)
        } else {
            debit.balance += MoneyUtils.computeBalanceDelta(debit.type, true, amount)
            credit.balance += MoneyUtils.computeBalanceDelta(credit.type, false, amount)
        }
        debit.modifiedAt = now
        credit.modifiedAt = now
        withContext(Dispatchers.IO) {
            dao.submitVoucherTransaction(voucher, debit, credit, true)
        }
    }

}
