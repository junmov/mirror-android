package cn.junmov.mirror.budget.domain

import cn.junmov.mirror.core.data.db.dao.AccountDao
import cn.junmov.mirror.core.data.db.entity.Account
import kotlinx.coroutines.flow.Flow

class FlowBudgetUseCase(private val dao: AccountDao) {

    operator fun invoke(id: Long): Flow<Account>{
        return dao.flowAccount(id)
    }

}
