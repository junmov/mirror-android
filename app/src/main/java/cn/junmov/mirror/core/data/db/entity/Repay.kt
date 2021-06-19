package cn.junmov.mirror.core.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import cn.junmov.mirror.core.data.db.Scheme
import com.google.gson.annotations.SerializedName
import java.time.LocalDate
import java.time.LocalDateTime

@Entity(
    tableName = Scheme.Repay.TABLE_NAME,
    indices = [
        Index(Scheme.Repay.DEBT_ID)
    ]
)
data class Repay(
    @SerializedName("rowId")
    @PrimaryKey @ColumnInfo(name = Scheme.ID) override val id: Long,
    @ColumnInfo(name = Scheme.Repay.DEBT_ID) override val debtId: Long,
    @ColumnInfo(name = Scheme.Repay.CAPITAL) override var capital: Int,
    @ColumnInfo(name = Scheme.Repay.INTEREST) override var interest: Int,
    @ColumnInfo(name = Scheme.Repay.DATE_AT) override val dateAt: LocalDate,
    @ColumnInfo(name = Scheme.Repay.IS_SETTLED) override var repaid: Boolean,
    @ColumnInfo(name = Scheme.CREATE_AT) override val createAt: LocalDateTime,
    @ColumnInfo(name = Scheme.MODIFIED_AT) override var modifiedAt: LocalDateTime,
    @ColumnInfo(name = Scheme.DEL) override var deleted: Boolean,
    override val borrowId: Long,
    override val borrowName: String,
    override val indexOfDebt: String,
) : RepayEntity
