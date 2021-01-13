package cn.junmov.mirror.debt.domain

import cn.junmov.mirror.core.data.db.dao.DebtDao
import cn.junmov.mirror.core.data.db.entity.Debt
import cn.junmov.mirror.core.data.db.entity.Repay
import java.time.LocalDate
import java.time.LocalDateTime

class PayDateRepayUseCase(private val debtDao: DebtDao) {
    suspend operator fun invoke(dateAt: LocalDate) {
        val repayAndDebts = debtDao.findAllRepayAndDebtByDate(dateAt)
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
        debtDao.payDateRepayTransaction(debts, repays)
    }
}
