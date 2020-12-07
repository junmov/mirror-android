package cn.junmov.mirror.asset.domain

import cn.junmov.mirror.core.data.dao.AssetDao
import cn.junmov.mirror.core.data.entity.Asset
import cn.junmov.mirror.core.utility.SnowFlakeUtil
import java.time.LocalDateTime

class CreateAssetUseCase(private val dao: AssetDao) {
    suspend operator fun invoke(name: String) {
        val now = LocalDateTime.now()
        val asset = Asset(
            id = SnowFlakeUtil.genId(), name = name, count = 0, buy = 0, sell = 0,
            createAt = now, modifiedAt = now, isDeleted = false
        )
        dao.insert(asset)
    }
}