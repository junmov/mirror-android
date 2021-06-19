package cn.junmov.mirror.core.data.db.entity

import java.time.LocalDate

/**
 * 债务分期还款
 */
interface RepayEntity : CommonField {
    /** 债务ID */
    val debtId: Long

    val borrowId: Long
    val borrowName: String

    val indexOfDebt: String

    /** 本期本金 */
    var capital: Int

    /** 本期利息 */
    var interest: Int

    /** 最后期限 */
    val dateAt: LocalDate

    /** 本期是否还清 */
    var repaid: Boolean
}