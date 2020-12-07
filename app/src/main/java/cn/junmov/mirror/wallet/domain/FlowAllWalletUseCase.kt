package cn.junmov.mirror.wallet.domain

import cn.junmov.mirror.core.data.AccountType
import cn.junmov.mirror.core.data.dao.AccountDao
import cn.junmov.mirror.core.data.model.Wallet
import kotlinx.coroutines.flow.Flow

class FlowAllWalletUseCase(private val dao: AccountDao) {

    operator fun invoke(): Flow<List<Wallet>> {
        return dao.flowAllWallet(*AccountType.wallets)
    }

}
