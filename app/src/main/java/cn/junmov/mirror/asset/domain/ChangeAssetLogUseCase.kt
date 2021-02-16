package cn.junmov.mirror.asset.domain

import cn.junmov.mirror.core.data.db.dao.AssetDao
import cn.junmov.mirror.core.data.db.entity.Asset
import cn.junmov.mirror.core.data.db.entity.AssetLog
import cn.junmov.mirror.core.data.db.entity.Split
import cn.junmov.mirror.core.data.db.entity.Voucher
import cn.junmov.mirror.core.utility.AssetVoucherFactory
import java.time.LocalDateTime

class ChangeAssetLogUseCase(private val dao: AssetDao) {
    suspend operator fun invoke(
        assetLog: AssetLog, shouldCreateVoucher: Boolean
    ) {
        var asset: Asset? = null
        var voucher: Voucher? = null
        var splits: List<Split>? = null
        val now = LocalDateTime.now()
        assetLog.modifiedAt = now
        assetLog.checkSuccess()
        if (assetLog.success) {
            asset = dao.findAsset(assetLog.assetId)
            asset.doLog(assetLog)
            asset.modifiedAt = now
        }
        if (shouldCreateVoucher && assetLog.amount > 0) {
            val voucherAndSplits = AssetVoucherFactory.createVoucher(assetLog)
            voucher = voucherAndSplits.voucher
            splits = voucherAndSplits.splits
        }
        dao.createAssetLogTransaction(asset, assetLog, voucher, splits)
    }
}
