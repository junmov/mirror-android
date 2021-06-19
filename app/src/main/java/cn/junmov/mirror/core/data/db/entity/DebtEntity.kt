package cn.junmov.mirror.core.data.db.entity

import java.time.LocalDate

/**
 * 债务
 */
interface DebtEntity : CommonField {
    /** 债务人 */
    val borrowId: Long
    val borrowName: String

    val borrowAt: LocalDate

    val remark:String

    /** 本金 */
    val capital: Int

    /** 已还本金 */
    var capitalRepaid: Int

    /** 已还利息 */
    var interestRepaid: Int

    /** 是否已结清 */
    var repaid: Boolean

}