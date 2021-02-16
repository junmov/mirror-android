package cn.junmov.mirror.core.utility

import cn.junmov.mirror.core.data.db.entity.AssetLog
import cn.junmov.mirror.core.data.db.entity.Split
import cn.junmov.mirror.core.data.db.entity.Voucher
import cn.junmov.mirror.core.data.model.VoucherAndSplit
import cn.junmov.mirror.core.data.model.VoucherType
import java.time.LocalDateTime

object AssetVoucherFactory {

    fun createVoucher(assetLog: AssetLog): VoucherAndSplit {
        val ids = SnowFlakeUtil.genIds(2)
        val now = assetLog.modifiedAt
        val voucher = getVoucher(ids[0], now, assetLog)
        val split = getSplit(ids[0], ids[1], now, assetLog)
        return VoucherAndSplit(voucher, listOf(split))
    }

    private fun getSplit(voucherId: Long, id: Long, now: LocalDateTime, assetLog: AssetLog): Split {
        return Split(
            id = id, voucherId = voucherId, amount = assetLog.amount, assetLog.buy,
            AccountEnum.FUND.id, 0L, AccountEnum.FUND.fullName, AccountEnum.FUND.type,
            createAt = now, modifiedAt = now, deleted = false
        )
    }

    private fun getVoucher(id: Long, now: LocalDateTime, assetLog: AssetLog): Voucher {
        val summary = if (assetLog.buy) {
            "申购-${assetLog.assetName}"
        } else {
            "赎回-${assetLog.assetName}"
        }
        return Voucher(
            id = id, summary = summary, dateAt = assetLog.dateAt, timeAt = now.toLocalTime(),
            audited = false, type = VoucherType.TRANSFER, profit = 0,
            thingId = ThingEnum.FINANCE.id, thingName = ThingEnum.FINANCE.thingName,
            createAt = now, modifiedAt = now, deleted = false
        )
    }

}