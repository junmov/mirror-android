package cn.junmov.mirror.core.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import cn.junmov.mirror.core.data.Scheme
import java.time.LocalDateTime
import java.time.YearMonth

@Entity(
    tableName = Scheme.Balance.TABLE_NAME,
    indices = [
        Index(
            value = [Scheme.Balance.MONTH_AT, Scheme.Balance.ACCOUNT_ID],
            unique = true
        )
    ]
)
data class Balance(
    @PrimaryKey @ColumnInfo(name = Scheme.ID) override val id: Long,
    @ColumnInfo(name = Scheme.Balance.MONTH_AT) override val monthAt: YearMonth,
    @ColumnInfo(name = Scheme.Balance.ACCOUNT_ID) override val accountId: Long,
    @ColumnInfo(name = Scheme.Balance.NAME) override val name: String,
    @ColumnInfo(name = Scheme.Balance.BASE) override val base: Int,
    @ColumnInfo(name = Scheme.Balance.INFLOW) override val inflow: Int,
    @ColumnInfo(name = Scheme.Balance.OUTFLOW) override val outflow: Int,
    @ColumnInfo(name = Scheme.Balance.TRADE_COUNT) override val tradeCount: Int,
    @ColumnInfo(name = Scheme.CREATE_AT) override val createAt: LocalDateTime = LocalDateTime.now(),
    @ColumnInfo(name = Scheme.MODIFIED_AT) override var modifiedAt: LocalDateTime = LocalDateTime.now(),
    @ColumnInfo(name = Scheme.DEL) override var isDeleted: Boolean = false
) : BalanceEntity

