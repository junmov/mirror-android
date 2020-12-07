package cn.junmov.mirror.debt.domain

import cn.junmov.mirror.core.data.dao.BillDao
import cn.junmov.mirror.core.data.dao.DebtDao
import cn.junmov.mirror.core.data.entity.Repay
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.time.LocalDateTime

class UpdateRepayUseCase(private val dao: DebtDao, private val billDao: BillDao) {
    suspend operator fun invoke(repay: Repay, amount: Int, interest: Int) {
        val now = LocalDateTime.now()
        val bill = billDao.find(repay.dateAt)
        bill.amount = bill.amount + amount - repay.capital + interest - repay.interest
        bill.modifiedAt = now
        repay.capital = amount
        repay.interest = interest
        repay.modifiedAt = now
        withContext(Dispatchers.IO) {
            dao.updateRepayTransaction(repay, bill)
        }
    }

}
