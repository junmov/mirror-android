package cn.junmov.mirror.budget.domain

import cn.junmov.mirror.core.data.dao.AccountDao
import cn.junmov.mirror.core.data.entity.Account
import kotlinx.coroutines.flow.Flow

class FlowBudgetUseCase(private val dao: AccountDao) {

    operator fun invoke(id: Long): Flow<Account>{
        return dao.flowAccount(id)
    }

}
