package cn.junmov.mirror.core.data.db.entity

import java.time.LocalDate
/**
 * 债务
 */
interface DebtEntity : CommonField {
    /** 描述 */
    var summary: String

    /** 本金 */
    val capital: Int

    /** 还款期数 */
    val count: Int

    /** 首期入账日 */
    val startAt: LocalDate

    /** 是否已结清 */
    var settled: Boolean

    /** 已还本金 */
    var capitalRepay: Int

    /** 已还利息 */
    var interestRepay: Int

    /** 已还期数 */
    var countRepay: Int

}