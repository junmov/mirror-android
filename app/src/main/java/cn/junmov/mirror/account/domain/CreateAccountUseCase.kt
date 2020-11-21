package cn.junmov.mirror.account.domain

import cn.junmov.mirror.core.data.AccountType
import cn.junmov.mirror.core.data.dao.AccountDao
import cn.junmov.mirror.core.data.dao.BudgetDao
import cn.junmov.mirror.core.data.entity.Account
import cn.junmov.mirror.core.data.entity.Budget
import cn.junmov.mirror.core.utility.SnowFlakeUtil
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.time.YearMonth

class CreateAccountUseCase(
    private val accountDao: AccountDao,
    private val budgetDao: BudgetDao
) {

    suspend operator fun invoke(account: Account) {
        var budget: Budget? = null
        // 设想以后一个账户类型对应一个表，
        // 支出应预算、负债应偿还、资产应买入、收入应规划，
        // 资金则按用途分日常开销、应急、投资
        when (account.type) {
            AccountType.SUPPLIES, AccountType.CONSUME -> {
                budget = createBudget(account)
            }
            else -> {
                // no-op
            }
        }
        withContext(Dispatchers.IO) {
            accountDao.createAccountTransaction(account, budget)
        }
    }

    private suspend fun createBudget(account: Account): Budget {
        val budgetTmp = Budget(
            id = SnowFlakeUtil.genId(), monthAt = YearMonth.now(), parentId = 0L,
            accountName = account.name, accountId = account.id,
            total = 0, delta = 0, used = 0,
        )
        return if (account.parentId == 0L) {
            budgetTmp
        } else {
            val parentBudget = budgetDao.findBudgetByAccountId(account.parentId, YearMonth.now())
            budgetTmp.copy(parentId = parentBudget.id)
        }
    }
}