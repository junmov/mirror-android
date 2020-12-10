package cn.junmov.mirror.budget.domain

import cn.junmov.mirror.core.data.dao.AccountDao
import cn.junmov.mirror.core.data.entity.Account
import kotlinx.coroutines.flow.Flow

class FlowAllSecondaryBudgetUseCase(private val dao: AccountDao) {

    operator fun invoke(parentId: Long): Flow<List<Account>> {
        return dao.flowAllByParent(parentId)
    }

}