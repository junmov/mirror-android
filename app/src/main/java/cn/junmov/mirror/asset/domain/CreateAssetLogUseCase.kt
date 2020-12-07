package cn.junmov.mirror.asset.domain

import cn.junmov.mirror.core.data.dao.AssetDao
import cn.junmov.mirror.core.data.entity.AssetLog

class CreateAssetLogUseCase(private val dao: AssetDao) {
    suspend operator fun invoke(assetLog: AssetLog) {
        val asset = dao.findAsset(assetLog.assetId)
        asset.doLog(assetLog)
        dao.createAssetLogTransaction(asset,assetLog)
    }
}
