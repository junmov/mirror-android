package cn.junmov.mirror.debt.domain

import cn.junmov.mirror.core.data.dao.DebtDao
import cn.junmov.mirror.core.data.model.DebtInfo
import kotlinx.coroutines.flow.Flow

class FlowDebtInfoUseCase(private val dao: DebtDao) {
    operator fun invoke(id: Long): Flow<DebtInfo> {
        return dao.flowAgingDebtInfo(id)
    }
}