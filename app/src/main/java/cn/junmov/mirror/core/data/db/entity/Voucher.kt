package cn.junmov.mirror.core.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import cn.junmov.mirror.core.data.db.Scheme
import cn.junmov.mirror.core.data.model.VoucherType
import com.google.gson.annotations.SerializedName
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
    @SerializedName("rowId")
    @PrimaryKey @ColumnInfo(name = Scheme.ID) override val id: Long,
    @ColumnInfo(name = Scheme.Voucher.SUMMARY) override var summary: String,
    @ColumnInfo(name = Scheme.Voucher.DATE_AT) override var dateAt: LocalDate,
    @ColumnInfo(name = Scheme.Voucher.TIME_AT) override var timeAt: LocalTime,
    @ColumnInfo(name = Scheme.Voucher.THING_ID) override var thingId: Long,
    @ColumnInfo(name = Scheme.Voucher.THING_NAME) override var thingName: String,
    @ColumnInfo(name = Scheme.Voucher.PROFIT) override var profit: Int,
    @ColumnInfo(name = Scheme.Voucher.TYPE, defaultValue = "''") override var type: VoucherType,
    @ColumnInfo(name = Scheme.Voucher.IS_AUDITED) override var audited: Boolean,
    @ColumnInfo(name = Scheme.Voucher.IS_TEMPLATE) override var template: Boolean,
    @ColumnInfo(name = Scheme.CREATE_AT) override val createAt: LocalDateTime = LocalDateTime.now(),
    @ColumnInfo(name = Scheme.MODIFIED_AT) override var modifiedAt: LocalDateTime = LocalDateTime.now(),
    @ColumnInfo(name = Scheme.DEL) override var deleted: Boolean = false,
) : VoucherEntity