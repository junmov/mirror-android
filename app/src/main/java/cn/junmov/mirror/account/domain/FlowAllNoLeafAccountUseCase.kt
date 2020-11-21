package cn.junmov.mirror.account.domain

import cn.junmov.mirror.core.data.dao.AccountDao
import cn.junmov.mirror.core.data.entity.Account
import kotlinx.coroutines.flow.Flow

class FlowAllNoLeafAccountUseCase(private val dao: AccountDao) {
    operator fun invoke(): Flow<List<Account>> {
        return dao.flowAllByLeaf(false)
    }
}
