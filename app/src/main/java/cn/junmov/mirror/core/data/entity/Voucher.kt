package cn.junmov.mirror.core.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import cn.junmov.mirror.core.adapter.TwoLineAble
import cn.junmov.mirror.core.adapter.TwoLineModel
import cn.junmov.mirror.core.data.Scheme
import cn.junmov.mirror.core.utility.MoneyUtils
import cn.junmov.mirror.core.utility.TimeUtils
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

@Entity(
    tableName = Scheme.Voucher.TABLE_NAME,
    indices = [
        Index(Scheme.Voucher.THING_ID)
    ]
)
data class Voucher(
    @PrimaryKey @ColumnInfo(name = Scheme.ID) override val id: Long,
    @ColumnInfo(name = Scheme.Voucher.SUMMARY) override var summary: String,
    @ColumnInfo(name = Scheme.Voucher.DATE_AT) override var dateAt: LocalDate,
    @ColumnInfo(name = Scheme.Voucher.TIME_AT) override var timeAt: LocalTime,
    @ColumnInfo(name = Scheme.Voucher.THING_ID) override var thingId: Long,
    @ColumnInfo(name = Scheme.Voucher.THING_NAME) override var thingName: String,
    @ColumnInfo(name = Scheme.Voucher.PROFIT) override var profit: Int,
    @ColumnInfo(name = Scheme.CREATE_AT) override val createAt: LocalDateTime = LocalDateTime.now(),
    @ColumnInfo(name = Scheme.MODIFIED_AT) override var modifiedAt: LocalDateTime = LocalDateTime.now(),
    @ColumnInfo(name = Scheme.DEL) override var isDeleted: Boolean = false
) : VoucherEntity, TwoLineAble {

    override fun toTwoLineUiModel(): TwoLineModel.UiData = TwoLineModel.UiData(
        id = id, primary = summary,
        secondary = "$thingName ${TimeUtils.dateToString(dateAt)}",
        action = MoneyUtils.centToYuan(profit), title = thingName,
        separator = TimeUtils.dateToString(dateAt)
    )

}