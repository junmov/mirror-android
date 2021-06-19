package cn.junmov.mirror.account.domain

import cn.junmov.mirror.core.data.db.dao.AccountDao
import cn.junmov.mirror.core.data.db.entity.Account
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CreateAccountUseCase(private val dao: AccountDao) {

    suspend operator fun invoke(account: Account) {
        withContext(Dispatchers.IO) { dao.insert(account) }
    }
}