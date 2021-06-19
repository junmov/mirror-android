package cn.junmov.mirror.asset.domain

import android.util.Log
import cn.junmov.mirror.core.data.db.dao.AssetDao
import cn.junmov.mirror.core.data.db.entity.Asset
import cn.junmov.mirror.core.data.db.entity.AssetLog
import cn.junmov.mirror.core.utility.SnowFlakeUtil
import java.time.LocalDate
import java.time.LocalDateTime

/**
 * 委托下单
 */
class EntrustOrderUseCase(private val dao: AssetDao) {
    suspend operator fun invoke(
        assetId: Long, assetName: String,
        isBuy: Boolean, count: Int, price: String,
        dateAt: LocalDate, reason: String
    ) {
        val now = LocalDateTime.now()
        val ids = SnowFlakeUtil.genIds(2)

        val isBuild = isBuy && assetId == 0L
        Log.i("assetId", "$assetId")
        val asset = if (isBuild) {
            Asset(
                id = ids[0], name = assetName, buildAt = dateAt, active = true,
                count = 0, buySum = 0, expenseSum = 0, sellSum = 0, profit = 0,
                createAt = now, modifiedAt = now, deleted = false
            )
        } else {
            dao.findAsset(assetId)
        }
        val assetLog = AssetLog(
            id = ids[1], asset.id, assetName = asset.name,
            count = count, buy = isBuy, price = price, reason = reason, dateAt = dateAt,
            amount = 0, expense = 0, success = false,
            createAt = now, modifiedAt = now, deleted = false
        )
        if (isBuild) {
            dao.createAssetTransaction(asset, assetLog)
        } else {
            dao.insertAssetLog(assetLog)
        }
    }

}
