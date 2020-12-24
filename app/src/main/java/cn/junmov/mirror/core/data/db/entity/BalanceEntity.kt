package cn.junmov.mirror.core.data.db.entity

import java.time.LocalDate

/**
 * 月度账户余额快照
 */
interface BalanceEntity : CommonField {

    /** 月份 */
    val startAt: LocalDate

    /** 月份 */
    val endAt: LocalDate

    /** 账户 */
    val accountId: Long

    /** 账户名称 */
    val name: String

    /** 初始余额或初始预算 */
    val base: Int

    /** 余额流入或预算增量 */
    val inflow: Int

    /** 余额流出或使用预算 */
    val outflow: Int

    /** 交易数 */
    val tradeCount: Int
}