package cn.junmov.mirror.account.domain

import cn.junmov.mirror.core.data.db.dao.AccountDao
import cn.junmov.mirror.core.data.db.entity.Account
import java.time.LocalDateTime

class UpdateWalletBalanceUseCase(private val dao: AccountDao) {
    suspend operator fun invoke(account: Account, newBalance: Int) {
        val now = LocalDateTime.now()
        account.modifiedAt = now
        account.balance = newBalance
        dao.update(account)
    }
}