package cn.junmov.mirror.voucher.domain

import cn.junmov.mirror.core.data.dao.VoucherDao
import cn.junmov.mirror.core.data.model.VoucherAndSplits
import kotlinx.coroutines.flow.Flow

class FlowVoucherAndSplitsUseCase(private val dao: VoucherDao) {
    operator fun invoke(voucherId: Long): Flow<VoucherAndSplits> {
        return dao.flowVoucherInfo(voucherId)
    }
}