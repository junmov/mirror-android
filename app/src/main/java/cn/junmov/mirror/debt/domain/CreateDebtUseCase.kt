package cn.junmov.mirror.debt.domain

import cn.junmov.mirror.core.data.db.dao.DebtDao
import cn.junmov.mirror.core.data.db.entity.Debt
import cn.junmov.mirror.core.data.db.entity.Repay
import cn.junmov.mirror.core.utility.SnowFlakeUtil
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.time.LocalDateTime

class CreateDebtUseCase(private val dao: DebtDao) {
    suspend operator fun invoke(debt: Debt, interest: Int) {
        val now = LocalDateTime.now()

        val repays = mutableListOf<Repay>()
        val amountAvg: Int = debt.capital.div(debt.count)
        val interestAvg: Int = interest.div(debt.count)

        withContext(Dispatchers.Default) {
            val size = debt.count
            val ids = SnowFlakeUtil.genIds(size * 2)
            val startAt = debt.startAt
            for ((j, i) in (0 until debt.count).withIndex()) {
                val dateAt = startAt.plusMonths(i.toLong())
                val item = Repay(
                    id = ids[j], summary = "${i + 1}/${size} ${debt.summary}", dateAt = dateAt,
                    interest = interestAvg, capital = amountAvg, debtId = debt.id,
                    settled = false, createAt = now, modifiedAt = now, deleted = false
                )
                repays.add(item)
            }
        }
        withContext(Dispatchers.IO) {
            dao.createAgingDebtTransaction(debt, repays)
        }
    }

}
