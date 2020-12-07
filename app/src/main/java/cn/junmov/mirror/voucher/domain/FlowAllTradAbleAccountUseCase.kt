package cn.junmov.mirror.voucher.domain

import cn.junmov.mirror.core.data.dao.AccountDao
import cn.junmov.mirror.core.data.entity.Account
import kotlinx.coroutines.flow.Flow

class FlowAllTradAbleAccountUseCase(private val dao: AccountDao) {
    operator fun invoke(): Flow<List<Account>> {
        return dao.flowAllTradAbleAccount()
    }
}
