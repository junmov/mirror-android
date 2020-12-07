package cn.junmov.mirror.core.data.entity

/**
 * 资产买卖记录
 */
interface AssetLogEntity : CommonField {

    /** 资产 */
    var assetId: Long

    /** 买或卖 */
    var isBuy: Boolean

    /** 数量 */
    var count: Int

    /** 市场单价 */
    var unitPrice: String

    /** 买入或卖出的金额 */
    var amount: Int

}