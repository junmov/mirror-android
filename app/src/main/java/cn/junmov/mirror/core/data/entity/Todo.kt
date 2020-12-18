package cn.junmov.mirror.core.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import cn.junmov.mirror.core.data.PeriodType
import cn.junmov.mirror.core.data.Scheme
import com.google.gson.annotations.SerializedName
import java.time.LocalDate
import java.time.LocalDateTime

@Entity(tableName = Scheme.ToDo.TABLE_NAME)
data class Todo(
    @SerializedName("rowId")
    @PrimaryKey @ColumnInfo(name = Scheme.ID) override val id: Long,
    @ColumnInfo(name = Scheme.ToDo.SUMMARY) override var summary: String,
    @ColumnInfo(name = Scheme.ToDo.IS_ENABLED) override var enabled: Boolean,
    @ColumnInfo(name = Scheme.ToDo.RUN_AT) override var runAt: LocalDate,
    @ColumnInfo(name = Scheme.ToDo.PERIOD) override var period: PeriodType,
    @ColumnInfo(name = Scheme.ToDo.DONE_TIMES) override var doneTimes: Int,
    @ColumnInfo(name = Scheme.ToDo.DONE_TOTAL) override var doneTotal: Int,
    @ColumnInfo(name = Scheme.CREATE_AT) override val createAt: LocalDateTime,
    @ColumnInfo(name = Scheme.MODIFIED_AT) override var modifiedAt: LocalDateTime,
    @ColumnInfo(name = Scheme.DEL) override var deleted: Boolean
) : ToDoEntity {

    fun done(dateTime: LocalDateTime) {
        modifiedAt = dateTime
        doneTimes += 1
        if (doneTotal == 0 || doneTimes < doneTotal) {
            runAt = period.nextPeriod(dateTime.toLocalDate())
        } else {
            enabled = false
        }
    }

}
