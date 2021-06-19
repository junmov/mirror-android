package cn.junmov.mirror.voucher.domain

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import cn.junmov.mirror.core.data.db.dao.VoucherDao
import cn.junmov.mirror.core.data.db.view.ItemVoucher
import kotlinx.coroutines.flow.Flow

class PagingVoucherInAccountUseCase(private val dao: VoucherDao) {

    operator fun invoke(accountId: Long, size: Int = 15): Flow<PagingData<ItemVoucher>> {
        return Pager(PagingConfig(size)) { dao.pagingVoucherInAccount(accountId) }.flow
    }

}