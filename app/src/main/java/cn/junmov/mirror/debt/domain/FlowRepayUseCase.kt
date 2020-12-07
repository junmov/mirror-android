package cn.junmov.mirror.debt.domain

import cn.junmov.mirror.core.data.dao.DebtDao
import cn.junmov.mirror.core.data.entity.Repay
import kotlinx.coroutines.flow.Flow

class FlowRepayUseCase(private val dao: DebtDao) {
    operator fun invoke(id: Long): Flow<Repay> {
        return dao.flowRepay(id)
    }
}
