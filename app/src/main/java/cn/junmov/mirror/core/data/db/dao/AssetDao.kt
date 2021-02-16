package cn.junmov.mirror.core.data.db.dao

import androidx.room.*
import cn.junmov.mirror.core.data.db.entity.Asset
import cn.junmov.mirror.core.data.db.entity.AssetLog
import cn.junmov.mirror.core.data.db.entity.Split
import cn.junmov.mirror.core.data.db.entity.Voucher
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
    suspend fun createAssetLogTransaction(
        asset: Asset?, assetLog: AssetLog, voucher: Voucher?, splits: List<Split>?
    ) {
        asset?.let { update(it) }
        insertAssetLog(assetLog)
        voucher?.let { insertVoucher(it) }
        splits?.let { insertSplits(it) }
    }

    @Insert
    suspend fun insertSplits(it: List<Split>)

    @Insert
    suspend fun insertVoucher(voucher: Voucher)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAssetLog(assetLog: AssetLog)

    @Query("select * from asset_log where row_id = :assetLogId")
    fun flowAssetLog(assetLogId: Long): Flow<AssetLog>
}