package cn.junmov.mirror.core.data.db.entity

import java.time.LocalDate

interface AssetEntity : CommonField {

    /** 资产名称 */
    val name: String

    /** 建仓日期 */
    val buildAt: LocalDate

    /** 状态 */
    var active: Boolean

    /** 持有数量 */
    var count: Int

    /** 买入合计 */
    var buySum: Int

    /** 卖出合计 */
    var sellSum: Int

    /** 费用合计 */
    var expenseSum: Int

    /** 盈亏 */
    var profit: Int

}