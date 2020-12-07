package cn.junmov.mirror.core.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import cn.junmov.mirror.core.data.Scheme
import java.time.LocalDateTime

@Entity(tableName = Scheme.Asset.TABLE_NAME)
data class Asset(
    @PrimaryKey @ColumnInfo(name = Scheme.ID) override val id: Long,
    @ColumnInfo(name = Scheme.Asset.NAME) override var name: String,
    @ColumnInfo(name = Scheme.Asset.COUNT) override var count: Int,
    @ColumnInfo(name = Scheme.Asset.BUY) override var buy: Int,
    @ColumnInfo(name = Scheme.Asset.SELL) override var sell: Int,
    @ColumnInfo(name = Scheme.CREATE_AT) override val createAt: LocalDateTime = LocalDateTime.now(),
    @ColumnInfo(name = Scheme.MODIFIED_AT) override var modifiedAt: LocalDateTime = LocalDateTime.now(),
    @ColumnInfo(name = Scheme.DEL) override var isDeleted: Boolean = false
) : AssetEntity {

    fun doLog(assetLog: AssetLog) {
        if (assetLog.isBuy) {
            buy += assetLog.amount
            count += assetLog.count
        } else {
            sell += assetLog.amount
            count -= assetLog.count
        }
        modifiedAt = assetLog.createAt
    }

}
