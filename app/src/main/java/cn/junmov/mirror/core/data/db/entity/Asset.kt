package cn.junmov.mirror.core.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import cn.junmov.mirror.core.data.db.Scheme
import com.google.gson.annotations.SerializedName
import java.time.LocalDateTime

@Entity(tableName = Scheme.Asset.TABLE_NAME)
data class Asset(
    @SerializedName("rowId")
    @PrimaryKey @ColumnInfo(name = Scheme.ID) override val id: Long,
    @ColumnInfo(name = Scheme.Asset.NAME) override var name: String,
    @ColumnInfo(name = Scheme.Asset.COUNT) override var count: Int,
    @ColumnInfo(name = Scheme.Asset.BUY) override var buy: Int,
    @ColumnInfo(name = Scheme.Asset.SELL) override var sell: Int,
    @ColumnInfo(name = Scheme.CREATE_AT) override val createAt: LocalDateTime = LocalDateTime.now(),
    @ColumnInfo(name = Scheme.MODIFIED_AT) override var modifiedAt: LocalDateTime = LocalDateTime.now(),
    @ColumnInfo(name = Scheme.DEL) override var deleted: Boolean = false
) : AssetEntity {

    fun change(isBuy: Boolean, countDelta: Int, amountDelta: Int) {
        if (isBuy) {
            buy += amountDelta
            count += countDelta
        } else {
            sell += amountDelta
            count -= countDelta
        }
    }

}
