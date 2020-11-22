package cn.junmov.mirror.home.domain

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import cn.junmov.mirror.core.data.dao.VoucherDao
import cn.junmov.mirror.core.data.entity.Voucher
import kotlinx.coroutines.flow.Flow

class PagingVoucherUseCase(private val dao: VoucherDao) {

    operator fun invoke(size: Int = 15): Flow<PagingData<Voucher>> {
        return Pager(PagingConfig(size)) { dao.pagingVoucher() }.flow
    }

}
