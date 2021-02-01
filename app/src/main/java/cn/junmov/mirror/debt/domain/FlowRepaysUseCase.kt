package cn.junmov.mirror.debt.domain

import cn.junmov.mirror.core.data.db.dao.DebtDao
import cn.junmov.mirror.core.data.db.entity.Repay
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

class FlowRepaysUseCase(private val dao: DebtDao) {

    operator fun invoke(dateAt: LocalDate): Flow<List<Repay>> {
        return dao.flowRepaysByDate(dateAt)
    }

}