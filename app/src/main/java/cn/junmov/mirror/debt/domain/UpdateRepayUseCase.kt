package cn.junmov.mirror.debt.domain

import cn.junmov.mirror.core.data.db.dao.DebtDao
import cn.junmov.mirror.core.data.db.entity.Repay
import java.time.LocalDateTime

class UpdateRepayUseCase(private val dao: DebtDao) {
    suspend operator fun invoke(repay: Repay, amount: Int, interest: Int) {
        val now = LocalDateTime.now()
        repay.capital = amount
        repay.interest = interest
        repay.modifiedAt = now
        dao.updateRepay(repay)
    }

}
