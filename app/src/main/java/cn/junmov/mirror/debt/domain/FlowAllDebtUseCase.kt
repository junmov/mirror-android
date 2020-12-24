package cn.junmov.mirror.debt.domain

import cn.junmov.mirror.core.data.db.dao.DebtDao
import cn.junmov.mirror.core.data.db.entity.Debt
import kotlinx.coroutines.flow.Flow

class FlowAllDebtUseCase (private val dao: DebtDao){
    operator fun invoke(): Flow<List<Debt>> {
        return dao.flowAllDebt()
    }
}
