package cn.junmov.mirror.core.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import cn.junmov.mirror.core.data.Scheme
import java.time.LocalDateTime
import java.time.YearMonth

@Entity(
    tableName = Scheme.Budget.TABLE_NAME,
    indices = [
        Index(value = [Scheme.Budget.MONTH_AT, Scheme.Budget.ACCOUNT_ID], unique = true)
    ]
)
data class Budget(
    @PrimaryKey @ColumnInfo(name = Scheme.ID) override val id: Long,
    @ColumnInfo(name = Scheme.Budget.MONTH_AT) override val monthAt: YearMonth,
    @ColumnInfo(name = Scheme.Budget.PARENT_ID) override val parentId: Long,
    @ColumnInfo(name = Scheme.Budget.ACCOUNT_ID) override val accountId: Long,
    @ColumnInfo(name = Scheme.Budget.ACCOUNT_NAME) override val accountName: String,
    @ColumnInfo(name = Scheme.Budget.TOTAL) override var total: Int,
    @ColumnInfo(name = Scheme.Budget.USED) override var used: Int,
    @ColumnInfo(name = Scheme.Budget.DELTA) override var delta: Int,
    @ColumnInfo(name = Scheme.CREATE_AT) override val createAt: LocalDateTime = LocalDateTime.now(),
    @ColumnInfo(name = Scheme.MODIFIED_AT) override var modifiedAt: LocalDateTime = LocalDateTime.now(),
    @ColumnInfo(name = Scheme.DEL) override var isDeleted: Boolean = false,
) : BudgetEntity {
    fun isSonOf(parent: Budget): Boolean = parentId == parent.id
}
