package cn.junmov.mirror.voucher.domain

import cn.junmov.mirror.core.data.dao.VoucherDao
import cn.junmov.mirror.core.utility.SnowFlakeUtil
import cn.junmov.mirror.core.data.model.VoucherAndSplits
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.time.LocalDateTime

class CopyVoucherUseCase(private val dao: VoucherDao) {
    suspend operator fun invoke(voucherInfo: VoucherAndSplits) {
        val now = LocalDateTime.now()
        val ids = SnowFlakeUtil.genIds(voucherInfo.splits.size + 1)
        val voucherId = ids[0]
        val newVoucher = voucherInfo.voucher.copy(
            id = voucherId, profit = 0, isAudited = false, isTemplate = false,isDeleted = false,
            dateAt = now.toLocalDate(), timeAt = now.toLocalTime(), createAt = now, modifiedAt = now
        )
        val newSplits = voucherInfo.splits.mapIndexed { index, split ->
            split.copy(
                id = ids[index + 1], voucherId = voucherId, createAt = now, modifiedAt = now
            )
        }
        withContext(Dispatchers.IO) {
            dao.copyTransaction(newVoucher, newSplits)
        }
    }
}