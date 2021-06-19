package cn.junmov.mirror.core.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import cn.junmov.mirror.core.data.db.Scheme
import cn.junmov.mirror.core.data.model.FourCellUiModel
import cn.junmov.mirror.core.utility.MoneyUtils
import cn.junmov.mirror.core.utility.TimeUtils
import com.google.gson.annotations.SerializedName
import java.time.LocalDate
import java.time.LocalDateTime

@Entity(tableName = Scheme.Debt.TABLE_NAME)
data class Debt(
    @SerializedName("rowId")
    @PrimaryKey @ColumnInfo(name = Scheme.ID) override val id: Long,
    @ColumnInfo(name = Scheme.Debt.BORROW_AT) override var borrowAt: LocalDate,
    @ColumnInfo(name = Scheme.Debt.BORROW_ID) override var borrowId: Long,
    @ColumnInfo(name = Scheme.Debt.BORROW_NAME) override var borrowName: String,
    @ColumnInfo(name = Scheme.Debt.CAPITAL) override var capital: Int,
    @ColumnInfo(name = Scheme.Debt.CAPITAL_REPAY) override var capitalRepaid: Int = 0,
    @ColumnInfo(name = Scheme.Debt.INTEREST_REPAY) override var interestRepaid: Int = 0,
    @ColumnInfo(name = Scheme.Debt.REMARK) override var remark: String,
    @ColumnInfo(name = Scheme.Debt.IS_REPAID) override var repaid: Boolean,
    @ColumnInfo(name = Scheme.CREATE_AT) override val createAt: LocalDateTime = LocalDateTime.now(),
    @ColumnInfo(name = Scheme.MODIFIED_AT) override var modifiedAt: LocalDateTime = LocalDateTime.now(),
    @ColumnInfo(name = Scheme.DEL) override var deleted: Boolean = false
) : DebtEntity, FourCellUiModel {

    fun capitalNoRepaid(): Int = capital - capitalRepaid

    fun repay(repay: Repay) {
        capitalRepaid += repay.capital
        interestRepaid += repay.interest
        if (capitalRepaid == capital) {
            repaid = true
        }
        modifiedAt = repay.modifiedAt
    }

    override fun primary(): String = "${TimeUtils.dateToString(borrowAt)} $borrowName"

    override fun secondary(): String = "已支付利息:${MoneyUtils.centToYuan(interestRepaid)}"

    override fun bottom(): String = MoneyUtils.centToYuan(capitalNoRepaid())

    override fun top(): String = MoneyUtils.centToYuan(capital)

}
