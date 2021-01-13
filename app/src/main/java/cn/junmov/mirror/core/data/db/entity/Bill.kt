package cn.junmov.mirror.core.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import cn.junmov.mirror.core.adapter.SingleLineAble
import cn.junmov.mirror.core.adapter.SingleLineModel
import cn.junmov.mirror.core.data.db.Scheme
import cn.junmov.mirror.core.utility.MoneyUtils
import cn.junmov.mirror.core.utility.TimeUtils
import com.google.gson.annotations.SerializedName
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.YearMonth

@Entity(tableName = Scheme.Bill.TABLE_NAME)
data class Bill(
    @SerializedName("rowId")
    @PrimaryKey @ColumnInfo(name = Scheme.ID) override val id: Long,
    @ColumnInfo(name = Scheme.Bill.DATE_AT) override val dateAt: LocalDate,
    @ColumnInfo(name = Scheme.Bill.AMOUNT) override var amount: Int,
    @ColumnInfo(name = Scheme.Bill.IS_SETTLED) override var settled: Boolean,
    @ColumnInfo(name = Scheme.CREATE_AT) override val createAt: LocalDateTime,
    @ColumnInfo(name = Scheme.MODIFIED_AT) override var modifiedAt: LocalDateTime,
    @ColumnInfo(name = Scheme.DEL) override var deleted: Boolean
) : BillEntity, SingleLineAble {
    override fun singleLineData(): SingleLineModel.UiData = SingleLineModel.UiData(
        id = id,
        primary = "${dateAt.dayOfMonth}日 应还:${MoneyUtils.centToYuan(amount)}",
        action = if (settled) "已还" else "待还",
        separator = TimeUtils.yearMonthToString(YearMonth.from(dateAt)),
        title = "${TimeUtils.dateToString(dateAt)} 应还:${MoneyUtils.centToYuan(amount)}"
    )
}