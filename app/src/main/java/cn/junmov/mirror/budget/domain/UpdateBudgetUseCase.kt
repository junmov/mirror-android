package cn.junmov.mirror.budget.domain

import cn.junmov.mirror.core.data.dao.BudgetDao
import cn.junmov.mirror.core.data.entity.Budget
import java.time.LocalDateTime

class UpdateBudgetUseCase(private val dao: BudgetDao) {
    suspend operator fun invoke(budget: Budget, amount: Int) {
        val now = LocalDateTime.now()
        val parent = dao.findById(budget.parentId)
        budget.total = amount
        budget.modifiedAt = now
        parent.total += amount
        parent.modifiedAt = now
        dao.update(parent, budget)
    }
}