package cn.junmov.mirror.voucher.domain

import cn.junmov.mirror.core.data.db.dao.VoucherDao
import cn.junmov.mirror.core.data.db.entity.Voucher
import kotlinx.coroutines.flow.Flow

class FlowVoucherUseCase(private val dao: VoucherDao) {
    operator fun invoke(voucherId: Long): Flow<Voucher> {
        return dao.flowVoucher(voucherId)
    }
}