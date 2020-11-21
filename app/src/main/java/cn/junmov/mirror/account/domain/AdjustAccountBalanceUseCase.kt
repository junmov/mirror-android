package cn.junmov.mirror.account.domain

import cn.junmov.mirror.core.data.dao.AccountDao
import cn.junmov.mirror.core.data.entity.Account
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.time.LocalDateTime

class AdjustAccountBalanceUseCase(private val dao: AccountDao) {

    suspend operator fun invoke(account: Account, newBalance: Int) {
        withContext(Dispatchers.IO) {
            account.balance = newBalance
            account.modifiedAt = LocalDateTime.now()
            dao.update(account)
        }
    }

}