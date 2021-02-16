package cn.junmov.mirror.core.data.db.entity

import java.time.LocalDate

/**
 * 资产买卖记录
 */
interface AssetLogEntity : CommonField {

    /** 交易发起的日期 */
    var dateAt: LocalDate

    /** 资产 */
    var assetId: Long

    /** 资产名称 */
    var assetName: String

    /** 买或卖 */
    var buy: Boolean

    /** 数量 */
    var count: Int

    /** 买入或卖出的金额 */
    var amount: Int

    /** 交易成功标志 */
    var success: Boolean

}