package cn.junmov.mirror.core.data.db.entity

import java.time.LocalDate
import java.time.LocalTime

interface VoucherEntity : CommonField {

    /** 时间 */
    var dateAt: LocalDate
    var timeAt: LocalTime

    /** 备注 */
    var summary: String

    /** 交易金额 */
    var amount: Int

    /** 借方账户 */
    var debitId: Long
    var debitName: String

    /** 贷方账户 */
    var creditId: Long
    var creditName: String

    /** 活动项目 */
    var thing: String

}