package cn.junmov.mirror.account.domain

import cn.junmov.mirror.core.data.db.dao.AccountDao
import cn.junmov.mirror.core.data.db.entity.Account
import kotlinx.coroutines.flow.Flow

class FlowAccountUseCase(private val dao: AccountDao) {

    operator fun invoke(id: Long): Flow<Account> {
        return dao.flowAccount(id)
    }

}