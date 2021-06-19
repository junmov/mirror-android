package cn.junmov.mirror.voucher.domain

import cn.junmov.mirror.core.data.db.dao.AccountDao
import cn.junmov.mirror.core.data.db.dao.VoucherDao
import cn.junmov.mirror.core.utility.SnowFlakeUtil
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.time.LocalDateTime

class CopyVoucherUseCase(private val dao: VoucherDao, private val accountDao: AccountDao) {
    suspend operator fun invoke(voucherId: Long) {
        val now = LocalDateTime.now()
        val oldItem = dao.findById(voucherId)
        val amount = oldItem.amount
        val newItem = oldItem.copy(
            id = SnowFlakeUtil.genId(), dateAt = now.toLocalDate(),
            timeAt = now.toLocalTime(), createAt = now, modifiedAt = now
        )

        val debit = accountDao.findById(oldItem.debitId)
        val credit = accountDao.findById(oldItem.creditId)

        debit.plusAmount(true, amount)
        debit.modifiedAt = now
        credit.plusAmount(false, amount)
        credit.modifiedAt = now

        withContext(Dispatchers.IO) {
            dao.submitVoucherTransaction(newItem, debit, credit)
        }

    }
}