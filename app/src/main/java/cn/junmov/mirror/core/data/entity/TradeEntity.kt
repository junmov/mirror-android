package cn.junmov.mirror.core.data.entity

import cn.junmov.mirror.core.data.AccountType
import java.time.LocalDate

/**
 * 一个账户在一笔交易中的余额变化
 */
interface TradeEntity : CommonField {
    /** 交易ID */
    val voucherId: Long

    /** 账户ID */
    val accountId: Long

    /** 账户类型 */
    val accountType: AccountType

    /** 变化金额 */
    var amount: Int

    /** 交易日期 */
    val dateAt: LocalDate
}