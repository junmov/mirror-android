package cn.junmov.mirror.home.domain

import cn.junmov.mirror.core.data.db.dao.VoucherDao
import cn.junmov.mirror.voucher.data.ItemVoucher
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

class FlowLastThreeVoucherUseCase(private val dao: VoucherDao) {
    operator fun invoke(): Flow<List<ItemVoucher>> {
        val today = LocalDate.now()
        val threeDaysAgo = today.minusDays(3)
        return dao.flowAllVoucherBetween(threeDaysAgo, today)
    }
}
