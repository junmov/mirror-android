package cn.junmov.mirror.core.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import cn.junmov.mirror.core.data.model.AccountType
import cn.junmov.mirror.core.data.db.Scheme
import cn.junmov.mirror.core.utility.MoneyUtils
import com.google.gson.annotations.SerializedName
import java.time.LocalDateTime

@Entity(
    tableName = Scheme.Account.TABLE_NAME,
    indices = [
        Index(value = [Scheme.Account.NAME], unique = true),
    ]
)
data class Account(
    @SerializedName("rowId")
    @PrimaryKey @ColumnInfo(name = Scheme.ID) override val id: Long,
    @ColumnInfo(name = Scheme.Account.NAME) override val name: String,
    @ColumnInfo(name = Scheme.Account.TYPE) override val type: AccountType,
    @ColumnInfo(name = Scheme.Account.BALANCE) override var balance: Int = 0,
    @ColumnInfo(name = Scheme.Account.BUDGET) override var budget: Int = 0,
    @ColumnInfo(name = Scheme.Account.DEBIT) override var debit: Int = 0,
    @ColumnInfo(name = Scheme.Account.CREDIT) override var credit: Int = 0,
    @ColumnInfo(name = Scheme.Account.TRADE_COUNT) override var tradeCount: Int = 0,
    @ColumnInfo(name = Scheme.CREATE_AT) override val createAt: LocalDateTime = LocalDateTime.now(),
    @ColumnInfo(name = Scheme.MODIFIED_AT) override var modifiedAt: LocalDateTime = LocalDateTime.now(),
    @ColumnInfo(name = Scheme.DEL) override var deleted: Boolean = false,
) : AccountEntity {

    fun plusAmount(isDebit: Boolean, amount: Int) {
        if (isDebit) {
            debit += amount
        } else {
            credit += amount
        }
        balance += MoneyUtils.computeBalanceDelta(type, isDebit, amount)
    }

    fun budgetUseAble(): Int = budget - debit + credit

    override fun toString(): String = name

}