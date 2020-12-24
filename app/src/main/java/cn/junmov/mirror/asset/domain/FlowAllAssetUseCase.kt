package cn.junmov.mirror.asset.domain

import cn.junmov.mirror.core.data.db.dao.AssetDao
import cn.junmov.mirror.core.data.db.entity.Asset
import kotlinx.coroutines.flow.Flow

class FlowAllAssetUseCase(private val dao: AssetDao) {
    operator fun invoke(): Flow<List<Asset>> {
        return dao.flowAllAsset()
    }
}
