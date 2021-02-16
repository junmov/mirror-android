package cn.junmov.mirror.asset.domain

import cn.junmov.mirror.core.data.db.dao.AssetDao
import cn.junmov.mirror.core.data.db.entity.AssetLog
import kotlinx.coroutines.flow.Flow

class FlowAssetLogUseCase(private val dao: AssetDao) {
    operator fun invoke(assetLogId: Long): Flow<AssetLog> {
        return dao.flowAssetLog(assetLogId)
    }
}
