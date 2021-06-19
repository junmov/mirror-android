package cn.junmov.mirror.asset.domain

import cn.junmov.mirror.core.data.db.dao.AssetDao
import cn.junmov.mirror.core.utility.MoneyUtils
import java.math.BigDecimal
import java.time.LocalDateTime

/**
 * 成交委托单
 */
class ClinchOrderUseCase(private val dao: AssetDao) {

    suspend operator fun invoke(orderId: Long, amountHappened: String) {
        val now = LocalDateTime.now()
        val assetLog = dao.findAssetLog(orderId)
        val asset = dao.findAsset(assetLog.assetId)

        // 成交金额
        val amountClinch = BigDecimal(assetLog.price).times(BigDecimal(assetLog.count))
        // 费用 = |成交金额 - 发生金额|
        val expense =
            MoneyUtils.yuanToCent(BigDecimal(amountHappened).minus(amountClinch).abs().toString())
        val amount = MoneyUtils.yuanToCent(amountHappened)

        assetLog.amount = amount
        assetLog.expense = expense

        if (assetLog.buy) {
            asset.count += assetLog.count
            asset.buySum += assetLog.amount
            asset.expenseSum += assetLog.expense
        } else {
            asset.count -= assetLog.count
            asset.sellSum += assetLog.amount
            if (asset.count == 0) {
                asset.profit = asset.sellSum - asset.buySum
                asset.active = false
            }
        }
        asset.modifiedAt = now

        assetLog.success = true
        assetLog.modifiedAt = now

        dao.submitAssetLogTransaction(asset, assetLog)
    }

}