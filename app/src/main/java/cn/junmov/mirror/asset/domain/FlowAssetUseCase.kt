package cn.junmov.mirror.asset.domain

import cn.junmov.mirror.core.data.dao.AssetDao
import cn.junmov.mirror.core.data.entity.Asset
import kotlinx.coroutines.flow.Flow

class FlowAssetUseCase(private val dao: AssetDao) {
    operator fun invoke(id: Long): Flow<Asset> {
        return dao.flowAsset(id)
    }
}
