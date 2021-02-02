package cn.junmov.mirror.debt.domain

import cn.junmov.mirror.core.data.db.dao.DebtDao
import cn.junmov.mirror.core.data.db.entity.Debt
import cn.junmov.mirror.core.data.db.entity.Repay
import java.time.LocalDateTime

class RemoveDebtUseCase(private val dao: DebtDao) {
    suspend operator fun invoke(debt: Debt, repays: List<Repay>) {
        val now = LocalDateTime.now()
        debt.deleted = true
        debt.modifiedAt = now
        repays.forEach {
            it.deleted = true
            it.modifiedAt = now
        }
        dao.removeDebtTransaction(debt, repays)
    }
}
