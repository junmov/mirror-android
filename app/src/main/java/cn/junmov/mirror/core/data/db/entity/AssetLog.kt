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
    @ColumnInfo(name = Scheme.AssetLog.ASSET_ID) override val assetId: Long,
    @ColumnInfo(name = Scheme.AssetLog.ASSET_NAME) override val assetName: String,
    @ColumnInfo(name = Scheme.AssetLog.DATE_AT) override var dateAt: LocalDate,
    @ColumnInfo(name = Scheme.AssetLog.IS_BUY) override var buy: Boolean,
    @ColumnInfo(name = Scheme.AssetLog.COUNT) override var count: Int,
    @ColumnInfo(name = Scheme.AssetLog.PRICE) override var price: String,
    @ColumnInfo(name = Scheme.AssetLog.EXPENSE) override var expense: Int,
    @ColumnInfo(name = Scheme.AssetLog.AMOUNT) override var amount: Int,
    @ColumnInfo(name = Scheme.AssetLog.REASON) override var reason: String,
    @ColumnInfo(name = Scheme.AssetLog.SUCCESS)   override var success: Boolean,
    @ColumnInfo(name = Scheme.CREATE_AT) override val createAt: LocalDateTime = LocalDateTime.now(),
    @ColumnInfo(name = Scheme.MODIFIED_AT) override var modifiedAt: LocalDateTime = LocalDateTime.now(),
    @ColumnInfo(name = Scheme.DEL) override var deleted: Boolean = false,
) : AssetLogEntity


