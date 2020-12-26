package cn.junmov.mirror.core.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import cn.junmov.mirror.core.adapter.TwoLineAble
import cn.junmov.mirror.core.adapter.TwoLineModel
import cn.junmov.mirror.core.data.model.AccountType
import cn.junmov.mirror.core.data.db.Scheme
import cn.junmov.mirror.core.utility.MoneyUtils
import com.google.gson.annotations.SerializedName
import java.time.LocalDateTime

@Entity(
    tableName = Scheme.Account.TABLE_NAME,
    indices = [
        Index(value = [Scheme.Account.NAME], unique = true),
        Index(value = [Scheme.Account.PARENT_ID])
    ]
)
data class Account(
    @SerializedName("rowId")
    @PrimaryKey @ColumnInfo(name = Scheme.ID) override val id: Long,
    @ColumnInfo(name = Scheme.Account.NAME) override val name: String,
    @ColumnInfo(name = Scheme.Account.FULL_NAME) override val fullName: String,
    @ColumnInfo(name = Scheme.Account.TYPE) override val type: AccountType,
    @ColumnInfo(name = Scheme.Account.PARENT_ID) override val parentId: Long,
    @ColumnInfo(name = Scheme.Account.TRAD_ABLE) override val tradAble: Boolean,
    @ColumnInfo(name = Scheme.Account.TRADE_COUNT) override var tradeCount: Int,
    @ColumnInfo(name = Scheme.Account.BASE) override var base: Int,
    @ColumnInfo(name = Scheme.Account.INFLOW) override var inflow: Int,
    @ColumnInfo(name = Scheme.Account.OUTFLOW) override var outflow: Int,
    @ColumnInfo(name = Scheme.CREATE_AT) override val createAt: LocalDateTime = LocalDateTime.now(),
    @ColumnInfo(name = Scheme.MODIFIED_AT) override var modifiedAt: LocalDateTime = LocalDateTime.now(),
    @ColumnInfo(name = Scheme.DEL) override var deleted: Boolean = false
) : AccountEntity, TwoLineAble {

    override fun twoLineData(): TwoLineModel.UiData = TwoLineModel.UiData(
        id = id, primary = name, secondary = type.toString(),
        action = MoneyUtils.centToYuan(base + inflow - outflow), title = name, separator = ""
    )

    fun plusAmount(amount: Int) {
        if (AccountType.wallets.contains(type)) {
            base += amount
            if (amount >= 0) {
                inflow += amount
            } else {
                outflow += amount
            }
        } else {
            outflow += amount
        }
    }

    fun recycle(dateTime: LocalDateTime) {
        inflow = 0
        outflow = 0
        modifiedAt = dateTime
    }

    override fun toString(): String = fullName

}