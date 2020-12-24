package cn.junmov.mirror.core.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import cn.junmov.mirror.core.data.db.entity.Asset
import cn.junmov.mirror.core.data.db.entity.AssetLog
import kotlinx.coroutines.flow.Flow

@Dao
interface AssetDao : BaseDao<Asset> {
    @Query("select * from asset where is_deleted = 0")
    fun flowAllAsset(): Flow<List<Asset>>

    @Query("select * from asset where row_id = :id")
    fun flowAsset(id: Long): Flow<Asset>

    @Query("select * from asset_log where asset_id = :assetId and is_deleted = 0 ")
    fun flowAllAssetLog(assetId: Long): Flow<List<AssetLog>>

    @Query("select * from asset where row_id = :assetId")
    suspend fun findAsset(assetId: Long): Asset

    @Transaction
    suspend fun createAssetLogTransaction(asset: Asset, assetLog: AssetLog) {
        update(asset)
        insertAssetLog(assetLog)
    }

    @Insert
    suspend fun insertAssetLog(assetLog: AssetLog)
}