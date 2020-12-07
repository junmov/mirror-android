package cn.junmov.mirror.voucher.data

import androidx.room.ColumnInfo
import cn.junmov.mirror.core.adapter.TwoLineAble
import cn.junmov.mirror.core.adapter.TwoLineModel
import cn.junmov.mirror.core.data.Scheme
import cn.junmov.mirror.core.utility.MoneyUtils
import cn.junmov.mirror.core.utility.TimeUtils
import java.time.LocalDate
import java.time.LocalTime

data class ItemVoucher(
    @ColumnInfo(name = Scheme.ID) val id: Long,
    @ColumnInfo(name = Scheme.Voucher.SUMMARY) val summary: String,
    @ColumnInfo(name = Scheme.Voucher.THING_NAME) val thing: String,
    @ColumnInfo(name = Scheme.Voucher.DATE_AT) val dateAt: LocalDate,
    @ColumnInfo(name = Scheme.Voucher.TIME_AT) val timeAt: LocalTime,
    @ColumnInfo(name = Scheme.Voucher.PROFIT) val profit: Int,
    @ColumnInfo(name = Scheme.Voucher.IS_AUDITED) val isAudited: Boolean
) : TwoLineAble {
    override fun twoLineData(): TwoLineModel.UiData = TwoLineModel.UiData(
        id = id, primary = summary,
        secondary = "$thing ${TimeUtils.dateToString(dateAt)}",
        action = MoneyUtils.centToYuan(profit), title = thing,
        separator = TimeUtils.dateToString(dateAt)
    )

}
