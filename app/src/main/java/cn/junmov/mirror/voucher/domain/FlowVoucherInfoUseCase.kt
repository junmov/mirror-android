package cn.junmov.mirror.voucher.domain

import cn.junmov.mirror.core.data.dao.VoucherDao
import cn.junmov.mirror.voucher.data.VoucherInfo
import kotlinx.coroutines.flow.Flow

class FlowVoucherInfoUseCase(private val dao: VoucherDao) {
    operator fun invoke(voucherId: Long): Flow<VoucherInfo> {
        return dao.flowVoucherInfo(voucherId)
    }
}