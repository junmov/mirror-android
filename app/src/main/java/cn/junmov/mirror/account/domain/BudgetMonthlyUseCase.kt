package cn.junmov.mirror.account.domain

import cn.junmov.mirror.core.data.db.dao.AccountDao
import java.time.LocalDateTime

class BudgetMonthlyUseCase(private val dao: AccountDao) {

    suspend operator fun invoke() {
        val now = LocalDateTime.now()
        val budgets = dao.listAllBudget()
        budgets.forEach {
            it.debit = 0
            it.credit = 0
            it.modifiedAt = now
        }
        dao.update(*budgets.toTypedArray())
    }

}