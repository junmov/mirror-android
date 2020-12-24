package cn.junmov.mirror.debt.domain

import cn.junmov.mirror.core.data.model.AccountType
import cn.junmov.mirror.core.data.db.dao.AccountDao
import cn.junmov.mirror.core.data.db.entity.Account
import kotlinx.coroutines.flow.Flow

class FlowAllDebtAccountUseCase(private val dao: AccountDao) {

    operator fun invoke(): Flow<List<Account>> {
        return dao.flowAllByTypeAndTradAble(true, AccountType.PAYABLE)
    }

}
