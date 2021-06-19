package cn.junmov.mirror.core.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import cn.junmov.mirror.core.data.db.Scheme
import cn.junmov.mirror.core.utility.AccountEnum
import cn.junmov.mirror.core.utility.Things
import com.google.gson.annotations.SerializedName
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

@Entity(
    tableName = Scheme.Voucher.TABLE_NAME,
)
data class Voucher(
    @SerializedName("rowId")
    @PrimaryKey @ColumnInfo(name = Scheme.ID) override val id: Long,
    @ColumnInfo(name = Scheme.Voucher.SUMMARY) override var summary: String,
    @ColumnInfo(name = Scheme.Voucher.AMOUNT) override var amount: Int,
    @ColumnInfo(name = Scheme.Voucher.DEBIT_ID) override var debitId: Long = AccountEnum.CATERING.id,
    @ColumnInfo(name = Scheme.Voucher.DEBIT_NAME) override var debitName: String = AccountEnum.CATERING.fullName,
    @ColumnInfo(name = Scheme.Voucher.CREDIT_ID) override var creditId: Long = AccountEnum.HUA_BEI.id,
    @ColumnInfo(name = Scheme.Voucher.CREDIT_NAME) override var creditName: String = AccountEnum.HUA_BEI.fullName,
    @ColumnInfo(name = Scheme.Voucher.DATE_AT) override var dateAt: LocalDate,
    @ColumnInfo(name = Scheme.Voucher.TIME_AT) override var timeAt: LocalTime,
    @ColumnInfo(name = Scheme.Voucher.THING) override var thing: String = Things.USUAL,
    @ColumnInfo(name = Scheme.CREATE_AT) override val createAt: LocalDateTime = LocalDateTime.now(),
    @ColumnInfo(name = Scheme.MODIFIED_AT) override var modifiedAt: LocalDateTime = LocalDateTime.now(),
    @ColumnInfo(name = Scheme.DEL) override var deleted: Boolean = false,
) : VoucherEntity