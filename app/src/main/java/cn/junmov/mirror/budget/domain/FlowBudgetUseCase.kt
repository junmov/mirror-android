package cn.junmov.mirror.budget.domain

import cn.junmov.mirror.core.data.dao.AccountDao
import cn.junmov.mirror.core.data.model.Category
import kotlinx.coroutines.flow.Flow

class FlowBudgetUseCase(private val dao: AccountDao) {

    operator fun invoke(id: Long): Flow<Category>{
        return dao.flowCategory(id)
    }

}
