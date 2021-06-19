package cn.junmov.mirror.core.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import cn.junmov.mirror.core.data.db.Scheme
import com.google.gson.annotations.SerializedName
import java.time.LocalDate
import java.time.LocalDateTime

@Entity(tableName = Scheme.Asset.TABLE_NAME)
data class Asset(
    @SerializedName("rowId")
    @PrimaryKey @ColumnInfo(name = Scheme.ID) override val id: Long,
    @ColumnInfo(name = Scheme.Asset.NAME) override val name: String,
    @ColumnInfo(name = Scheme.Asset.BUILD_AT) override val buildAt: LocalDate,
    @ColumnInfo(name = Scheme.Asset.IS_ACTIVE) override var active: Boolean,
    @ColumnInfo(name = Scheme.Asset.COUNT) override var count: Int,
    @ColumnInfo(name = Scheme.Asset.BUY) override var buySum: Int,
    @ColumnInfo(name = Scheme.Asset.SELL) override var sellSum: Int,
    @ColumnInfo(name = Scheme.Asset.EXPENSE) override var expenseSum: Int,
    @ColumnInfo(name = Scheme.Asset.PROFIT) override var profit: Int,
    @ColumnInfo(name = Scheme.CREATE_AT) override val createAt: LocalDateTime,
    @ColumnInfo(name = Scheme.MODIFIED_AT) override var modifiedAt: LocalDateTime,
    @ColumnInfo(name = Scheme.DEL) override var deleted: Boolean,
) : AssetEntity {

    override fun toString(): String = name

}
