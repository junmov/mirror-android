package cn.junmov.mirror.voucher.domain

import cn.junmov.mirror.core.data.db.dao.VoucherDao
import cn.junmov.mirror.core.data.db.entity.Split
import kotlinx.coroutines.flow.Flow

class FlowAllSplitByVoucherUseCase(private val dao: VoucherDao) {

    operator fun invoke(voucherId: Long): Flow<List<Split>> {
        return dao.flowAllSplitByVoucher(voucherId)
    }

}