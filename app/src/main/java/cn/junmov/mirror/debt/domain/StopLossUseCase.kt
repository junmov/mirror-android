package cn.junmov.mirror.debt.domain

import cn.junmov.mirror.core.data.dao.BillDao
import cn.junmov.mirror.core.data.dao.DebtDao
import cn.junmov.mirror.core.data.entity.Debt
import cn.junmov.mirror.core.data.entity.Repay
import cn.junmov.mirror.core.utility.SnowFlakeUtil
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.time.LocalDateTime

class StopLossUseCase(private val dao: DebtDao, private val billDao: BillDao) {
    /**
     * 结清[debt]的剩余未结本金以及已经产生的[interest]
     * 1.将所有未结清的分期条目以及该分期条目相关的交易删除
     * 2.产生一个新的未结清的包含[debt]剩余本金及[interest]的分期条目及其相关的交易
     */
    suspend operator fun invoke(debt: Debt, interest: Int) {
        val now = LocalDateTime.now()
        val repaySummary = "提前还款-${debt.summary}"
        val surplusAmount = debt.capital - debt.capitalRepay
        val items = dao.listAllNoSettledRepay(debt.id)
        val billDate = items.map { it.dateAt }
        val bills = billDao.listAll(billDate)
        for (i in items) {
            i.settled = true
            i.deleted = true
            i.modifiedAt = now
            for (b in bills) {
                if (i.dateAt == b.dateAt) {
                    b.amount = b.amount - i.capital - i.interest
                    b.modifiedAt = now
                    break
                }
            }
        }
        val repayItem = Repay(
            id = SnowFlakeUtil.genId(), debtId = debt.id,
            summary = repaySummary, capital = surplusAmount, interest = interest,
            dateAt = now.toLocalDate(), settled = true,
            createAt = now, modifiedAt = now, deleted = false
        )
        debt.capitalRepay += surplusAmount
        debt.interestRepay += interest
        debt.settled = true
        debt.modifiedAt = now
        withContext(Dispatchers.IO) {
            dao.stopLossTransaction(debt, items, repayItem, bills)
        }
    }
}