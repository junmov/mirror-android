package cn.junmov.mirror.budget.domain

import cn.junmov.mirror.core.data.db.dao.AccountDao
import cn.junmov.mirror.core.data.db.entity.Account
import java.time.LocalDateTime

class UpdateBudgetUseCase(private val dao: AccountDao) {

    suspend operator fun invoke(category: Account, budget: Int) {
        val now = LocalDateTime.now()
        val parent = dao.findById(category.parentId)
        val delta = budget - category.base
        category.base = budget
        category.modifiedAt = now
        parent.base += delta
        parent.modifiedAt = now
        dao.update(category, parent)
    }

}
