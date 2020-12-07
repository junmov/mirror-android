package cn.junmov.mirror.debt.domain

import cn.junmov.mirror.core.data.dao.BillDao
import cn.junmov.mirror.core.data.dao.DebtDao
import cn.junmov.mirror.core.data.entity.Bill
import cn.junmov.mirror.core.data.entity.Debt
import cn.junmov.mirror.core.data.entity.Repay
import cn.junmov.mirror.core.utility.SnowFlakeUtil
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.time.LocalDateTime

class CreateDebtUseCase(private val dao: DebtDao, private val billDao: BillDao) {
    suspend operator fun invoke(debt: Debt) {
        val now = LocalDateTime.now()

        val repays = mutableListOf<Repay>()
        val amountAvg: Int = debt.capital.div(debt.count)
        val interestAvg: Int = debt.interest.div(debt.count)

        val start = debt.startAt
        val end = start.plusMonths(debt.count.toLong())
        val oldBills = billDao.listAllBetween(start, end)
        val debtBills = mutableListOf<Bill>()
        val diffBills = mutableListOf<Bill>()

        withContext(Dispatchers.Default) {
            val size = debt.count
            val ids = SnowFlakeUtil.genIds(size * 2)
            val startAt = debt.startAt
            var j = 0
            for (i in 0 until debt.count) {
                val dateAt = startAt.plusMonths(i.toLong())
                val item = Repay(
                    id = ids[j++], summary = "${i + 1}/${size} ${debt.summary}", dateAt = dateAt,
                    interest = interestAvg, capital = amountAvg, debtId = debt.id,
                    isSettled = false, createAt = now, modifiedAt = now, isDeleted = false
                )
                repays.add(item)

                val bill = Bill(
                    id = ids[j++], amount = amountAvg + interestAvg, isSettled = false,
                    dateAt = dateAt, createAt = now, modifiedAt = now, isDeleted = false
                )
                debtBills.add(bill)
            }

            for (db in debtBills) {
                var exist = false
                for (old in oldBills) {
                    if (old.dateAt == db.dateAt) {
                        exist = true
                        old.amount += db.amount
                        old.modifiedAt = now
                        diffBills.add(old)
                        break
                    }
                }
                if (!exist) {
                    diffBills.add(db)
                }
            }
        }

        withContext(Dispatchers.IO) {
            dao.createAgingDebtTransaction(debt, repays, diffBills)
        }
    }

}
