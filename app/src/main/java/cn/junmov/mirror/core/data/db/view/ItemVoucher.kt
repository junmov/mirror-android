package cn.junmov.mirror.core.data.db.view

import androidx.room.ColumnInfo
import androidx.room.DatabaseView
import cn.junmov.mirror.core.data.db.Scheme
import cn.junmov.mirror.core.data.model.FourCellUiModel
import cn.junmov.mirror.core.utility.MoneyUtils
import cn.junmov.mirror.core.utility.TimeUtils
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

@DatabaseView(
    value = """
        SELECT row_id, summary, thing, date_at, time_at, amount, debit_name, credit_name,debit_id,credit_id
        FROM voucher 
        WHERE is_deleted = 0 
    """,
    viewName = Scheme.Voucher.VIEW_NAME
)
data class ItemVoucher(
    @ColumnInfo(name = Scheme.ID) val id: Long,
    @ColumnInfo(name = Scheme.Voucher.SUMMARY) val summary: String,
    @ColumnInfo(name = Scheme.Voucher.THING) val thing: String,
    @ColumnInfo(name = Scheme.Voucher.DATE_AT) val dateAt: LocalDate,
    @ColumnInfo(name = Scheme.Voucher.TIME_AT) val timeAt: LocalTime,
    @ColumnInfo(name = Scheme.Voucher.AMOUNT) val amount: Int,
    @ColumnInfo(name = Scheme.Voucher.DEBIT_ID) val debitId: Long,
    @ColumnInfo(name = Scheme.Voucher.DEBIT_NAME) val debit: String,
    @ColumnInfo(name = Scheme.Voucher.CREDIT_ID) val creditId: Long,
    @ColumnInfo(name = Scheme.Voucher.CREDIT_NAME) val credit: String,
) : FourCellUiModel {

    override fun primary(): String = summary

    override fun secondary(): String = "$thing ${
        TimeUtils.dateTimeToString(LocalDateTime.of(dateAt, timeAt))
    }"

    override fun top(): String = MoneyUtils.centToYuan(amount)

    override fun bottom(): String = "$credit -> $debit"

}
