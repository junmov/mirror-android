package cn.junmov.mirror.account.domain

import cn.junmov.mirror.account.data.AccountWithChildren
import cn.junmov.mirror.core.data.dao.AccountDao
import kotlinx.coroutines.flow.Flow

class FlowAccountWithChildrenUseCase(private val dao: AccountDao) {

    operator fun invoke(id: Long): Flow<AccountWithChildren> {
        return dao.flowAccountWithChildren(id)
    }

}