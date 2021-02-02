package cn.junmov.mirror.budget.domain

import cn.junmov.mirror.budget.data.Budget
import cn.junmov.mirror.core.data.model.AccountType
import cn.junmov.mirror.core.data.db.dao.AccountDao
import cn.junmov.mirror.core.data.db.entity.Account
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class FlowAllFirstBudgetUseCase(private val dao: AccountDao) {
    operator fun invoke(): Flow<List<Budget>> {
        return dao.flowFirstBudget()
    }
}
