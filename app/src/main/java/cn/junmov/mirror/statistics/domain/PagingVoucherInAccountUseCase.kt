package cn.junmov.mirror.statistics.domain

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import cn.junmov.mirror.core.data.db.dao.AccountDao
import cn.junmov.mirror.voucher.data.ItemVoucher
import kotlinx.coroutines.flow.Flow

class PagingVoucherInAccountUseCase(private val dao: AccountDao) {

    operator fun invoke(accountId: Long, size: Int = 15): Flow<PagingData<ItemVoucher>> {
        return Pager(PagingConfig(size)) { dao.pagingAccountTrading(accountId) }.flow
    }

}