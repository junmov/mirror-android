package cn.junmov.mirror.core.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import cn.junmov.mirror.core.data.Scheme
import com.google.gson.annotations.SerializedName
import java.time.LocalDate
import java.time.LocalDateTime

@Entity(
    tableName = Scheme.Balance.TABLE_NAME,
    indices = [
        Index(
            value = [Scheme.Balance.START_AT, Scheme.Balance.ACCOUNT_ID],
            unique = true
        )
    ]
)
data class Balance(
    @SerializedName("rowId")
    @PrimaryKey @ColumnInfo(name = Scheme.ID) override val id: Long,
    @ColumnInfo(name = Scheme.Balance.START_AT) override val startAt: LocalDate,
    @ColumnInfo(name = Scheme.Balance.END_AT) override val endAt: LocalDate,
    @ColumnInfo(name = Scheme.Balance.ACCOUNT_ID) override val accountId: Long,
    @ColumnInfo(name = Scheme.Balance.NAME) override val name: String,
    @ColumnInfo(name = Scheme.Balance.BASE) override var base: Int,
    @ColumnInfo(name = Scheme.Balance.INFLOW) override val inflow: Int,
    @ColumnInfo(name = Scheme.Balance.OUTFLOW) override val outflow: Int,
    @ColumnInfo(name = Scheme.Balance.TRADE_COUNT) override val tradeCount: Int,
    @ColumnInfo(name = Scheme.CREATE_AT) override val createAt: LocalDateTime = LocalDateTime.now(),
    @ColumnInfo(name = Scheme.MODIFIED_AT) override var modifiedAt: LocalDateTime = LocalDateTime.now(),
    @ColumnInfo(name = Scheme.DEL) override var deleted: Boolean = false
) : BalanceEntity


