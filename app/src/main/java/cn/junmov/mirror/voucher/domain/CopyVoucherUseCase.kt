package cn.junmov.mirror.voucher.domain

import cn.junmov.mirror.core.utility.SnowFlakeUtil
import cn.junmov.mirror.voucher.data.VoucherInfo
import java.time.LocalDateTime

class CopyVoucherUseCase(private val audit: AuditVoucherUseCase) {
    suspend operator fun invoke(voucherInfo: VoucherInfo) {
        val now = LocalDateTime.now()
        val ids = SnowFlakeUtil.genIds(voucherInfo.splits.size + 1)
        val voucherId = ids[0]
        val voucher = voucherInfo.voucher.copy(
            id = voucherId, createAt = now, modifiedAt = now, profit = 0,
            dateAt = now.toLocalDate(), timeAt = now.toLocalTime()
        )
        val splits = voucherInfo.splits.mapIndexed { index, split ->
            split.copy(
                id = ids[index + 1], voucherId = voucherId, createAt = now, modifiedAt = now
            )
        }
        audit(voucher, splits)
    }
}