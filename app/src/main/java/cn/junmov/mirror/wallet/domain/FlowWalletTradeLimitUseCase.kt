package cn.junmov.mirror.wallet.domain

import cn.junmov.mirror.core.data.db.dao.AccountDao
import cn.junmov.mirror.voucher.data.ItemVoucher
import kotlinx.coroutines.flow.Flow

class FlowWalletTradeLimitUseCase(private val dao: AccountDao) {

    operator fun invoke(id: Long, limit: Int): Flow<List<ItemVoucher>> {
        return dao.flowAccountTradeLimit(id, limit)
    }

}