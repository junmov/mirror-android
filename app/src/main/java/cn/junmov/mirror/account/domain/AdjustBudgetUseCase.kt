package cn.junmov.mirror.account.domain

import cn.junmov.mirror.core.data.db.dao.AccountDao
import cn.junmov.mirror.core.utility.MoneyUtils
import java.time.LocalDateTime

class AdjustBudgetUseCase(private val dao: AccountDao) {

    suspend operator fun invoke(categoryId: Long, amountYuan: String) {
        val account = dao.findById(categoryId)
        val now = LocalDateTime.now()
        account.budget = MoneyUtils.yuanToCent(amountYuan)
        account.modifiedAt = now
        dao.update(account)
    }

}
