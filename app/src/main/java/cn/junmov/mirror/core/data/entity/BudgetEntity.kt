package cn.junmov.mirror.core.data.entity

import java.time.YearMonth

interface BudgetEntity : CommonField {
    /** 预算月份 */
    val monthAt: YearMonth

    /** 父级预算 */
    val parentId: Long

    /** 参与预算的账户ID */
    val accountId: Long

    /** 参与预算的账户名称 */
    val accountName: String

    /** 基本预算 预算总额 = 基本预算 + 预算偏移量 */
    var total: Int

    /** 已使用预算 */
    var used: Int

    /** 预算偏移量 */
    var delta: Int

}