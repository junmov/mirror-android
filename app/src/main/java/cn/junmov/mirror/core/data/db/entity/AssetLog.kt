package cn.junmov.mirror.core.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import cn.junmov.mirror.core.data.db.Scheme
import com.google.gson.annotations.SerializedName
import java.time.LocalDate
import java.time.LocalDateTime

@Entity(tableName = Scheme.AssetLog.TABLE_NAME)
data class AssetLog(
    @SerializedName("rowId")
    @PrimaryKey @ColumnInfo(name = Scheme.ID) override val id: Long,
    @ColumnInfo(name = Scheme.AssetLog.ASSET_ID) override var assetId: Long,
    @ColumnInfo(name = Scheme.AssetLog.ASSET_NAME, defaultValue = "''")
    override var assetName: String,
    @ColumnInfo(name = Scheme.AssetLog.DATE_AT, defaultValue = "'2020-07-01'")
    override var dateAt: LocalDate,
    @ColumnInfo(name = Scheme.AssetLog.IS_BUY) override var buy: Boolean,
    @ColumnInfo(name = Scheme.AssetLog.COUNT) override var count: Int,
    @ColumnInfo(name = Scheme.AssetLog.AMOUNT) override var amount: Int,
    @ColumnInfo(name = Scheme.AssetLog.IS_SUCCESS, defaultValue = "1")
    override var success: Boolean,
    @ColumnInfo(name = Scheme.CREATE_AT) override val createAt: LocalDateTime = LocalDateTime.now(),
    @ColumnInfo(name = Scheme.MODIFIED_AT) override var modifiedAt: LocalDateTime = LocalDateTime.now(),
    @ColumnInfo(name = Scheme.DEL) override var deleted: Boolean = false,
) : AssetLogEntity {

    fun checkSuccess() {
        success = count > 0 && amount > 0
    }
}
