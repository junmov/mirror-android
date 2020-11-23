package cn.junmov.mirror.statistics.domain

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import cn.junmov.mirror.core.data.dao.StatisticsDao
import cn.junmov.mirror.core.data.entity.Voucher
import kotlinx.coroutines.flow.Flow

class PagingAccountTradingUseCase(private val dao: StatisticsDao) {

    operator fun invoke(accountId: Long, size: Int = 15): Flow<PagingData<Voucher>> {
        return Pager(PagingConfig(size)) { dao.pagingAccountTrading(accountId) }.flow
    }

}