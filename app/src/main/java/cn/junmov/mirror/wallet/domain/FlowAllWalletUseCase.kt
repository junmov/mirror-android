package cn.junmov.mirror.wallet.domain

import cn.junmov.mirror.core.data.AccountType
import cn.junmov.mirror.core.data.dao.AccountDao
import cn.junmov.mirror.core.data.entity.Account
import kotlinx.coroutines.flow.Flow

class FlowAllWalletUseCase(private val dao: AccountDao) {

    operator fun invoke(): Flow<List<Account>> {
        return dao.flowAllByTypeAndTradAble(true, *AccountType.wallets)
    }

}
