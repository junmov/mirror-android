package cn.junmov.mirror.wallet.domain

import cn.junmov.mirror.core.data.dao.AccountDao
import cn.junmov.mirror.core.data.entity.Account
import kotlinx.coroutines.flow.Flow

class FlowWalletUseCase(private val dao: AccountDao) {
    operator fun invoke(walletId: Long): Flow<Account> {
        return dao.flowAccount(walletId)
    }
}
