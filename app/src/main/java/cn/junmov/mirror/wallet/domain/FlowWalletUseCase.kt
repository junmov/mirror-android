package cn.junmov.mirror.wallet.domain

import cn.junmov.mirror.core.data.dao.AccountDao
import cn.junmov.mirror.core.data.model.Wallet
import kotlinx.coroutines.flow.Flow

class FlowWalletUseCase(private val dao: AccountDao) {
    operator fun invoke(walletId: Long): Flow<Wallet> {
        return dao.flowWallet(walletId)
    }
}
