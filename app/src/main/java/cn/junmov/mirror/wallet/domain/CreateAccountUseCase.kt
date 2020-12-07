package cn.junmov.mirror.wallet.domain

import cn.junmov.mirror.core.data.dao.AccountDao
import cn.junmov.mirror.core.data.entity.Account
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CreateAccountUseCase(private val dao: AccountDao) {

    suspend operator fun invoke(account: Account) {
        withContext(Dispatchers.IO) { dao.insert(account) }
    }
}