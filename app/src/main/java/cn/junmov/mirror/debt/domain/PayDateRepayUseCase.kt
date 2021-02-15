package cn.junmov.mirror.debt.domain

import cn.junmov.mirror.core.data.db.dao.DebtDao
import cn.junmov.mirror.core.data.db.entity.Debt
import cn.junmov.mirror.core.data.db.entity.Repay
import cn.junmov.mirror.core.utility.VoucherFactory
import java.time.LocalDate
import java.time.LocalDateTime

class PayDateRepayUseCase(private val debtDao: DebtDao) {
    suspend operator fun invoke(dateAt: LocalDate) {
        val repayAndDebts = debtDao.findAllRepayAndDebtByDate(dateAt)
        val now = LocalDateTime.now()
        val repays = mutableListOf<Repay>()
        val debts = mutableListOf<Debt>()
        var capital = 0
        var interest = 0
        repayAndDebts.forEach {
            val repay = it.repay
            repay.modifiedAt = now
            repay.settled = true
            repays.add(repay)
            capital += repay.capital
            interest += repay.interest
            val debt = it.debt
            debt.repay(repay)
            debts.add(debt)
        }
        val voucherAndSplits = VoucherFactory.createVoucher(dateAt, capital, interest)
        debtDao.payDateRepayTransaction(
            debts, repays, voucherAndSplits.voucher, voucherAndSplits.splits
        )
    }
}
