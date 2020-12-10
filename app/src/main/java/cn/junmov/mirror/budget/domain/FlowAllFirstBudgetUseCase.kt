package cn.junmov.mirror.budget.domain

import cn.junmov.mirror.core.data.AccountType
import cn.junmov.mirror.core.data.dao.AccountDao
import cn.junmov.mirror.core.data.entity.Account
import kotlinx.coroutines.flow.Flow

class FlowAllFirstBudgetUseCase(private val dao: AccountDao) {
    operator fun invoke(): Flow<List<Account>> {
        return dao.flowAllByTypeAndTradAble(false, *AccountType.categorys)
    }
}
