package cn.junmov.mirror.voucher.domain

import cn.junmov.mirror.core.data.db.dao.VoucherDao
import cn.junmov.mirror.core.data.db.entity.Split
import cn.junmov.mirror.core.data.db.entity.Voucher
import cn.junmov.mirror.core.utility.SnowFlakeUtil
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.time.LocalDateTime

class CopyVoucherUseCase(private val dao: VoucherDao) {
    suspend operator fun invoke(voucher: Voucher, splits: List<Split>) {
        val now = LocalDateTime.now()
        val ids = SnowFlakeUtil.genIds(splits.size + 1)
        val voucherId = ids[0]
        val newVoucher = voucher.copy(
            id = voucherId, profit = 0, audited = false, template = false, deleted = false,
            dateAt = now.toLocalDate(), timeAt = now.toLocalTime(), createAt = now, modifiedAt = now
        )
        val newSplits = splits.mapIndexed { index, split ->
            split.copy(
                id = ids[index + 1], voucherId = voucherId, createAt = now, modifiedAt = now
            )
        }
        withContext(Dispatchers.IO) {
            dao.copyTransaction(newVoucher, newSplits)
        }
    }
}