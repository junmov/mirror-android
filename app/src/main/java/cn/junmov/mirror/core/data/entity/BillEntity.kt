package cn.junmov.mirror.core.data.entity

import java.time.LocalDate

/**
 * 每月应付帐单和每月应收帐单
 */
interface BillEntity : CommonField {
    /** 还款日 */
    val dateAt: LocalDate

    /** 累计金额 */
    var amount: Int

    /** 是否结清 */
    var settled: Boolean

}