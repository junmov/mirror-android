package cn.junmov.mirror.account.domain

import cn.junmov.mirror.core.data.model.AccountType
import cn.junmov.mirror.core.data.db.dao.AccountDao
import cn.junmov.mirror.core.data.db.entity.Account
import kotlinx.coroutines.flow.Flow

class FlowAllWalletUseCase(private val dao: AccountDao) {

    operator fun invoke(): Flow<List<Account>> {
        return dao.flowAllByType(*AccountType.wallets)
    }

}
