package cn.junmov.mirror.budget.domain

import cn.junmov.mirror.core.data.dao.AccountDao
import cn.junmov.mirror.core.data.model.Category
import kotlinx.coroutines.flow.Flow

class FlowAllSecondaryBudgetUseCase(private val dao: AccountDao) {

    operator fun invoke(parentId: Long): Flow<List<Category>> {
        return dao.flowAllSecondaryBudget(parentId)
    }

}