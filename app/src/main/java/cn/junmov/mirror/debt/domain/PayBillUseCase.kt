package cn.junmov.mirror.debt.domain

import cn.junmov.mirror.core.data.dao.BillDao
import cn.junmov.mirror.core.data.dao.DebtDao
import cn.junmov.mirror.core.data.entity.Debt
import cn.junmov.mirror.core.data.entity.Repay
import java.time.LocalDateTime

class PayBillUseCase(private val billDao: BillDao, private val debtDao: DebtDao) {
    suspend operator fun invoke(billId: Long) {
        val bill = billDao.findById(billId)
        val repayAndDebts = debtDao.findAllRepayAndDebtByDate(bill.dateAt)
        val now = LocalDateTime.now()
        val repays = mutableListOf<Repay>()
        val debts = mutableListOf<Debt>()
        repayAndDebts.forEach {
            val repay = it.repay
            repay.modifiedAt = now
            repay.settled = true
            repays.add(repay)
            val debt = it.debt
            debt.repay(repay)
            debts.add(debt)
        }
        bill.settled = true
        bill.modifiedAt = now
        debtDao.payBillTransaction(bill, debts, repays)
    }
}
