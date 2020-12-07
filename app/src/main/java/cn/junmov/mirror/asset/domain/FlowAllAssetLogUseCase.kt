package cn.junmov.mirror.asset.domain

import cn.junmov.mirror.core.data.dao.AssetDao
import cn.junmov.mirror.core.data.entity.AssetLog
import kotlinx.coroutines.flow.Flow

class FlowAllAssetLogUseCase(private val dao: AssetDao) {
    operator fun invoke(assetId: Long): Flow<List<AssetLog>> {
        return dao.flowAllAssetLog(assetId)
    }
}
