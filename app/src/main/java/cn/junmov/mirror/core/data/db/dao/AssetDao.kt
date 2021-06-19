package cn.junmov.mirror.core.data.db.dao

import androidx.room.*
import cn.junmov.mirror.core.data.db.entity.Asset
import cn.junmov.mirror.core.data.db.entity.AssetLog
import kotlinx.coroutines.flow.Flow

@Dao
interface AssetDao : BaseDao<Asset> {
    @Query("select * from asset where is_deleted = 0 order by build_at desc")
    fun flowAllAsset(): Flow<List<Asset>>

    @Query("select * from asset_log where asset_id = :assetId and is_deleted = 0 order by date_at desc")
    fun flowAllLogOfAsset(assetId: Long): Flow<List<AssetLog>>

    @Query("select * from asset where row_id = :assetId")
    suspend fun findAsset(assetId: Long): Asset

    @Transaction
    suspend fun createAssetTransaction(asset: Asset, assetLog: AssetLog) {
        insert(asset)
        insertAssetLog(assetLog)
    }

    @Transaction
    suspend fun submitAssetLogTransaction(asset: Asset, assetLog: AssetLog) {
        update(asset)
        updateAssetLog(assetLog)
    }

    @Update
    suspend fun updateAssetLog(assetLog: AssetLog)

    @Insert
    suspend fun insertAssetLog(assetLog: AssetLog)

    @Query("select * from asset_log where is_deleted = 0 order by date_at desc")
    fun flowAllAssetLog(): Flow<List<AssetLog>>

    @Query("select * from asset where is_active = 1 and is_deleted = 0")
    fun flowAllHoldAssets(): Flow<List<Asset>>

    @Query("select * from asset_log where row_id = :id")
    suspend fun findAssetLog(id: Long): AssetLog
}