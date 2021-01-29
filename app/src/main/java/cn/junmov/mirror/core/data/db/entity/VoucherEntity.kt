package cn.junmov.mirror.core.data.db.entity

import cn.junmov.mirror.core.data.model.VoucherType
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
    var audited: Boolean

    /** 是否为模板 */
    var template: Boolean

    /**
     * 此交易产生的利润
     * 利润 = 收入 - 支出
     */
    var profit: Int

    /**
     * 交易类型，
     * 正利润为收、
     * 负利润为支、
     * 0为转
     */
    var type: VoucherType

}