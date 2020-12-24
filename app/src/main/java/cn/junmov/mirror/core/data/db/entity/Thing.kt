package cn.junmov.mirror.core.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import cn.junmov.mirror.core.adapter.SingleLineAble
import cn.junmov.mirror.core.adapter.SingleLineModel
import cn.junmov.mirror.core.data.db.Scheme
import cn.junmov.mirror.core.utility.TimeUtils
import com.google.gson.annotations.SerializedName
import java.time.LocalDateTime

@Entity(tableName = Scheme.Thing.TABLE_NAME)
data class Thing(
    @SerializedName("rowId")
    @PrimaryKey @ColumnInfo(name = Scheme.ID) override val id: Long,
    @ColumnInfo(name = Scheme.Thing.NAME) override var name: String,
    @ColumnInfo(name = Scheme.CREATE_AT) override val createAt: LocalDateTime = LocalDateTime.now(),
    @ColumnInfo(name = Scheme.MODIFIED_AT) override var modifiedAt: LocalDateTime = LocalDateTime.now(),
    @ColumnInfo(name = Scheme.DEL) override var deleted: Boolean = false
) : ThingEntity, SingleLineAble {

    override fun singleLineData(): SingleLineModel.UiData = SingleLineModel.UiData(
        id = id, primary = name, action = "", title = name,
        separator = TimeUtils.dateToString(modifiedAt.toLocalDate())
    )


    override fun toString(): String = name
}
