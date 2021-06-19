package cn.junmov.mirror.account.domain

import cn.junmov.mirror.core.data.db.dao.AccountDao
import cn.junmov.mirror.core.data.db.entity.Account
import cn.junmov.mirror.core.data.model.AccountType
import kotlinx.coroutines.flow.Flow

class FlowAllBudgetUseCase(private val dao: AccountDao) {

    operator fun invoke(): Flow<List<Account>> {
        return dao.flowAllBudget(AccountType.CONSUME)
    }

}
