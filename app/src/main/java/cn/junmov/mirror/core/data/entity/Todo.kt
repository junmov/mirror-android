package cn.junmov.mirror.core.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import cn.junmov.mirror.core.data.PeriodType
import cn.junmov.mirror.core.data.Scheme
import java.time.LocalDate
import java.time.LocalDateTime

@Entity(tableName = Scheme.ToDo.TABLE_NAME)
data class Todo(
    @PrimaryKey @ColumnInfo(name = Scheme.ID) override val id: Long,
    @ColumnInfo(name = Scheme.ToDo.SUMMARY) override var summary: String,
    @ColumnInfo(name = Scheme.ToDo.IS_ENABLED) override var isEnabled: Boolean,
    @ColumnInfo(name = Scheme.ToDo.RUN_AT) override var runAt: LocalDate,
    @ColumnInfo(name = Scheme.ToDo.PERIOD) override var period: PeriodType,
    @ColumnInfo(name = Scheme.ToDo.DONE_TIMES) override var doneTimes: Int,
    @ColumnInfo(name = Scheme.ToDo.DONE_TOTAL) override var doneTotal: Int,
    @ColumnInfo(name = Scheme.ToDo.IS_DONE) override var isDone: Boolean,
    @ColumnInfo(name = Scheme.CREATE_AT) override val createAt: LocalDateTime,
    @ColumnInfo(name = Scheme.MODIFIED_AT) override var modifiedAt: LocalDateTime,
    @ColumnInfo(name = Scheme.DEL) override var isDeleted: Boolean
) : ToDoEntity {

    fun isPerformable(date: LocalDate): Boolean {
        if (!isEnabled) return false
        if (runAt.compareTo(date) != 0) return false
        if (doneTotal != 0 && doneTimes >= doneTotal) return false
        return true
    }

    fun checkEnable() {
        if (doneTotal != 0 && doneTimes >= doneTotal) {
            isEnabled = false
        }
    }

    fun done(dateTime: LocalDateTime) {
        modifiedAt = dateTime
        isDone = true
        doneTimes += 1
        if (doneTotal == 0 || doneTimes < doneTotal) {
            runAt = period.nextPeriod(dateTime.toLocalDate())
        } else {
            isEnabled = false
        }
    }

}
