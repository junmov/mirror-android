package cn.junmov.mirror.core.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import cn.junmov.mirror.core.data.AccountType
import cn.junmov.mirror.core.data.Scheme
import java.time.LocalDate
import java.time.LocalDateTime

@Entity(
    tableName = Scheme.Trade.TABLE_NAME,
    indices = [
        Index(value = [Scheme.Trade.VOUCHER_ID, Scheme.Trade.ACCOUNT_ID], unique = true)
    ]
)
data class Trade(
    @PrimaryKey @ColumnInfo(name = Scheme.ID) override var id: Long,
    @ColumnInfo(name = Scheme.Trade.VOUCHER_ID) override val voucherId: Long,
    @ColumnInfo(name = Scheme.Trade.ACCOUNT_ID) override val accountId: Long,
    @ColumnInfo(name = Scheme.Trade.ACCOUNT_TYPE) override val accountType: AccountType,
    @ColumnInfo(name = Scheme.Trade.AMOUNT) override var amount: Int,
    @ColumnInfo(name = Scheme.Trade.DATE_AT) override val dateAt: LocalDate,
    @ColumnInfo(name = Scheme.CREATE_AT) override val createAt: LocalDateTime = LocalDateTime.now(),
    @ColumnInfo(name = Scheme.MODIFIED_AT) override var modifiedAt: LocalDateTime = LocalDateTime.now(),
    @ColumnInfo(name = Scheme.DEL) override var isDeleted: Boolean = false
) : TradeEntity