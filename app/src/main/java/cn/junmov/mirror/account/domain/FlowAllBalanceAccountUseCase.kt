package cn.junmov.mirror.account.domain

import cn.junmov.mirror.core.data.AccountType
import cn.junmov.mirror.core.data.dao.AccountDao
import cn.junmov.mirror.core.data.entity.Account
import kotlinx.coroutines.flow.Flow

class FlowAllBalanceAccountUseCase(private val dao: AccountDao) {

    operator fun invoke(): Flow<List<Account>> {
        return dao.flowAllByTypeAndLeaf(
            isLeaf = true,
            AccountType.FUND, AccountType.INVEST, AccountType.RECEIVABLE,
            AccountType.DEBT, AccountType.PAYABLE
        )
    }

}
