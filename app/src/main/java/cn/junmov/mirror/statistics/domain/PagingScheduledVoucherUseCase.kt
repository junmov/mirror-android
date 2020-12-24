package cn.junmov.mirror.statistics.domain

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import cn.junmov.mirror.core.data.db.dao.VoucherDao
import cn.junmov.mirror.voucher.data.ItemVoucher
import kotlinx.coroutines.flow.Flow

class PagingScheduledVoucherUseCase(private val dao: VoucherDao) {

    operator fun invoke(size: Int = 15): Flow<PagingData<ItemVoucher>> {
        return Pager(PagingConfig(size)) { dao.pagingVoucher(false) }.flow
    }

}
