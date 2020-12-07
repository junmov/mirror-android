package cn.junmov.mirror.core.data.entity

import java.time.LocalDate
import java.time.LocalTime

interface VoucherEntity : CommonField {
    /** 交易摘要 */
    var summary: String

    /** 交易日期 */
    var dateAt: LocalDate

    /** 交易时间 */
    var timeAt: LocalTime

    /** 项目ID */
    var thingId: Long

    /** 项目名称 */
    var thingName: String

    /** 是否已入账 */
    var isAudited: Boolean

    /** 是否为模板 */
    var isTemplate: Boolean

    /**
     * 此交易产生的利润
     * 利润 = 收入 - 支出
     */
    var profit: Int

}