package cn.junmov.mirror.core.data.db.entity

import java.time.LocalDate

/**
 * 资产买卖记录
 */
interface AssetLogEntity : CommonField {

    /** 日期 */
    val dateAt: LocalDate

    /** 资产 */
    val assetId: Long

    /** 资产名称 */
    val assetName: String

    /** 买或卖 */
    val buy: Boolean

    /** 数量 */
    val count: Int

    /** 市场价 */
    val price: String

    /** 交易费用 */
    var expense: Int

    /** 发生金额 */
    var amount: Int

    /** 买卖依据 */
    var reason: String

    /** 交易是否成功 */
    var success: Boolean
}