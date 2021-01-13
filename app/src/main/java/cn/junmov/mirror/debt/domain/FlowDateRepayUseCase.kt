package cn.junmov.mirror.debt.domain

import cn.junmov.mirror.core.data.db.dao.DebtDao
import cn.junmov.mirror.debt.data.DateRepay
import kotlinx.coroutines.flow.Flow

class FlowDateRepayUseCase(private val dao: DebtDao) {
    operator fun invoke(settled: Boolean = false): Flow<List<DateRepay>> {
        return dao.flowDateRepay(settled)
    }
}
