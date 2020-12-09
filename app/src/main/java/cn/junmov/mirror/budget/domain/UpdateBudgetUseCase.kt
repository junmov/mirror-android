package cn.junmov.mirror.budget.domain

import cn.junmov.mirror.core.data.dao.AccountDao
import cn.junmov.mirror.core.data.model.Category
import java.time.LocalDateTime

class UpdateBudgetUseCase(private val dao: AccountDao) {

    suspend operator fun invoke(category: Category, budget: Int) {
        val now = LocalDateTime.now()
        val parent = dao.findById(category.account.parentId)
        val delta = budget - category.account.base
        category.account.base = budget
        category.account.modifiedAt = now
        parent.account.base += delta
        parent.account.modifiedAt = now
        dao.update(category.account, parent.account)
    }

}
