package cn.junmov.mirror.core.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import cn.junmov.mirror.core.adapter.TwoLineAble
import cn.junmov.mirror.core.adapter.TwoLineModel
import cn.junmov.mirror.core.data.Scheme
import cn.junmov.mirror.core.utility.MoneyUtils
import com.google.gson.annotations.SerializedName
import java.time.LocalDate
import java.time.LocalDateTime

@Entity(tableName = Scheme.Debt.TABLE_NAME)
data class Debt(
    @SerializedName("rowId")
    @PrimaryKey @ColumnInfo(name = Scheme.ID) override val id: Long,
    @ColumnInfo(name = Scheme.Debt.SUMMARY) override var summary: String,
    @ColumnInfo(name = Scheme.Debt.ACCOUNT_ID) override val accountId: Long,
    @ColumnInfo(name = Scheme.Debt.START_AT) override val startAt: LocalDate,
    @ColumnInfo(name = Scheme.Debt.CAPITAL) override val capital: Int,
    @ColumnInfo(name = Scheme.Debt.CAPITAL_REPAY) override var capitalRepay: Int,
    @ColumnInfo(name = Scheme.Debt.COUNT) override val count: Int,
    @ColumnInfo(name = Scheme.Debt.COUNT_REPAY) override var countRepay: Int,
    @ColumnInfo(name = Scheme.Debt.INTEREST) override val interest: Int,
    @ColumnInfo(name = Scheme.Debt.INTEREST_REPAY) override var interestRepay: Int,
    @ColumnInfo(name = Scheme.Debt.IS_SETTLED) override var settled: Boolean,
    @ColumnInfo(name = Scheme.CREATE_AT) override val createAt: LocalDateTime = LocalDateTime.now(),
    @ColumnInfo(name = Scheme.MODIFIED_AT) override var modifiedAt: LocalDateTime = LocalDateTime.now(),
    @ColumnInfo(name = Scheme.DEL) override var deleted: Boolean = false,
) : DebtEntity, TwoLineAble {

    override fun twoLineData(): TwoLineModel.UiData = TwoLineModel.UiData(
        id = id, primary = summary,
        secondary = "已还本金:${MoneyUtils.centToYuan(capitalRepay)} 利息:${
            MoneyUtils.centToYuan(interestRepay)
        }",
        action = if (settled) "" else "未还",
        separator = "", title = ""
    )

    fun repay(repay: Repay) {
        capitalRepay += repay.capital
        interestRepay += repay.interest
        countRepay++
        if (capitalRepay == capital) {
            settled = true
        }
        modifiedAt = repay.modifiedAt
    }

}
