package cn.junmov.mirror.account.domain

import cn.junmov.mirror.core.data.dao.AccountDao
import cn.junmov.mirror.core.data.entity.Account
import kotlinx.coroutines.flow.Flow

class FlowAllAccountByLeafUseCase(private val dao: AccountDao) {
    operator fun invoke(isLeaf: Boolean = false): Flow<List<Account>> {
        return dao.flowAllByLeaf(isLeaf)
    }
}
