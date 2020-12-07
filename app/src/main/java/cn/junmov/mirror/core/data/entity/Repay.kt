package cn.junmov.mirror.core.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import cn.junmov.mirror.core.data.Scheme
import java.time.LocalDate
import java.time.LocalDateTime

@Entity(
    tableName = Scheme.Repay.TABLE_NAME,
    indices = [
        Index(Scheme.Repay.DEBT_ID)
    ]
)
data class Repay(
    @PrimaryKey @ColumnInfo(name = Scheme.ID) override val id: Long,
    @ColumnInfo(name = Scheme.Repay.DEBT_ID) override val debtId: Long,
    @ColumnInfo(name = Scheme.Repay.SUMMARY) override val summary: String,
    @ColumnInfo(name = Scheme.Repay.INTEREST) override var interest: Int,
    @ColumnInfo(name = Scheme.Repay.CAPITAL) override var capital: Int,
    @ColumnInfo(name = Scheme.Repay.DATE_AT) override val dateAt: LocalDate,
    @ColumnInfo(name = Scheme.Repay.IS_SETTLED) override var isSettled: Boolean,
    @ColumnInfo(name = Scheme.CREATE_AT) override val createAt: LocalDateTime,
    @ColumnInfo(name = Scheme.MODIFIED_AT) override var modifiedAt: LocalDateTime,
    @ColumnInfo(name = Scheme.DEL) override var isDeleted: Boolean,
) : RepayEntity
