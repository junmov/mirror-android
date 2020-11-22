package cn.junmov.mirror.budget.domain

import cn.junmov.mirror.core.data.dao.BudgetDao
import cn.junmov.mirror.core.data.entity.Budget
import kotlinx.coroutines.flow.Flow
import java.time.YearMonth

class FlowAllBudgetByParentUseCase(private val dao: BudgetDao) {

    operator fun invoke(parentId: Long = 0L): Flow<List<Budget>> {
        return dao.flowAllByParent(parentId, YearMonth.now())
    }

}