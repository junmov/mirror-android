package cn.junmov.mirror.budget.domain

import cn.junmov.mirror.core.data.dao.BudgetDao
import cn.junmov.mirror.core.data.entity.Budget
import kotlinx.coroutines.flow.Flow

class FlowBudgetUseCase(private val dao: BudgetDao) {
    operator fun invoke(budgetId: Long): Flow<Budget> {
        return dao.flowBudget(budgetId)
    }
}