package cn.junmov.mirror.voucher.domain

import cn.junmov.mirror.core.data.db.dao.VoucherDao
import cn.junmov.mirror.core.data.db.view.ItemVoucher
import kotlinx.coroutines.flow.Flow

class FlowAccountTradeLimitUseCase(private val dao: VoucherDao) {

    operator fun invoke(id: Long, limit: Int): Flow<List<ItemVoucher>> {
        return dao.flowVoucherInAccountLimit(id, limit)
    }

}