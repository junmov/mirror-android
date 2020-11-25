package cn.junmov.mirror.account.domain

import cn.junmov.mirror.core.data.AccountType
import cn.junmov.mirror.core.data.dao.AccountDao
import cn.junmov.mirror.core.data.entity.Account
import kotlinx.coroutines.flow.Flow

class FlowAllCategoryAccountUseCase(private val dao: AccountDao) {

    operator fun invoke(): Flow<List<Account>> {
        return dao.flowAllByTypeAndLeaf(
            isLeaf = false,
            AccountType.CONSUME, AccountType.EXPENSE,
            AccountType.ACTIVE, AccountType.PASSIVE
        )
    }

}
