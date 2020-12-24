package cn.junmov.mirror.budget.domain

import cn.junmov.mirror.core.data.model.AccountType
import cn.junmov.mirror.core.data.db.dao.AccountDao
import cn.junmov.mirror.core.data.db.entity.Account
import kotlinx.coroutines.flow.Flow

class FlowAllFirstBudgetUseCase(private val dao: AccountDao) {
    operator fun invoke(): Flow<List<Account>> {
        return dao.flowAllByTypeAndTradAble(false, *AccountType.categorys)
    }
}
