package cn.junmov.mirror.voucher.domain

import cn.junmov.mirror.core.data.db.dao.AccountDao
import cn.junmov.mirror.core.data.db.entity.Account
import kotlinx.coroutines.flow.Flow

class FlowAllAccountTradeCountDescUseCase(private val dao: AccountDao) {
    operator fun invoke():Flow<List<Account>> {
        return dao.flowAllAccountTradeCountDesc()
    }
}
