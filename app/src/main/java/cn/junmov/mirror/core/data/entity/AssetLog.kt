package cn.junmov.mirror.core.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import cn.junmov.mirror.core.data.Scheme
import com.google.gson.annotations.SerializedName
import java.time.LocalDateTime

@Entity(tableName = Scheme.AssetLog.TABLE_NAME)
data class AssetLog(
    @SerializedName("rowId")
    @PrimaryKey @ColumnInfo(name = Scheme.ID) override val id: Long,
    @ColumnInfo(name = Scheme.AssetLog.ASSET_ID) override var assetId: Long,
    @ColumnInfo(name = Scheme.AssetLog.IS_BUY) override var buy: Boolean,
    @ColumnInfo(name = Scheme.AssetLog.COUNT) override var count: Int,
    @ColumnInfo(name = Scheme.AssetLog.UNIT_PRICE) override var unitPrice: String,
    @ColumnInfo(name = Scheme.AssetLog.AMOUNT) override var amount: Int,
    @ColumnInfo(name = Scheme.CREATE_AT) override val createAt: LocalDateTime = LocalDateTime.now(),
    @ColumnInfo(name = Scheme.MODIFIED_AT) override var modifiedAt: LocalDateTime = LocalDateTime.now(),
    @ColumnInfo(name = Scheme.DEL) override var deleted: Boolean = false,
) : AssetLogEntity
