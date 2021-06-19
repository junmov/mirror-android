package cn.junmov.mirror.account.domain

import cn.junmov.mirror.core.data.db.dao.AccountDao
import cn.junmov.mirror.core.data.db.entity.Account
import cn.junmov.mirror.core.data.model.AccountType
import kotlinx.coroutines.flow.Flow

class FlowAllAccountByTypeUseCase(private val dao: AccountDao) {

    operator fun invoke(vararg types: AccountType): Flow<List<Account>> {
        return dao.flowAllByType(*types)
    }

}
