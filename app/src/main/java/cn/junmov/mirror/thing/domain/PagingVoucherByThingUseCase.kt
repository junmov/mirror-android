package cn.junmov.mirror.thing.domain

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import cn.junmov.mirror.core.data.dao.ThingDao
import cn.junmov.mirror.core.data.entity.Voucher
import kotlinx.coroutines.flow.Flow

class PagingVoucherByThingUseCase(private val dao: ThingDao) {

    operator fun invoke(thingId: Long, size: Int = 20): Flow<PagingData<Voucher>> {
        return Pager(PagingConfig(size)) { dao.pagingVoucherByThing(thingId) }.flow
    }
}