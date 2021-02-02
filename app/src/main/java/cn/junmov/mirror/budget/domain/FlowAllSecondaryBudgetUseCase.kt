package cn.junmov.mirror.budget.domain

import cn.junmov.mirror.budget.data.Budget
import cn.junmov.mirror.core.data.db.dao.AccountDao
import kotlinx.coroutines.flow.Flow

class FlowAllSecondaryBudgetUseCase(private val dao: AccountDao) {

    operator fun invoke(parentId: Long): Flow<List<Budget>> {
        return dao.flowSecondaryBudget(parentId)
    }

}