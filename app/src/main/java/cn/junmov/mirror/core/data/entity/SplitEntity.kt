package cn.junmov.mirror.core.data.entity

import cn.junmov.mirror.core.data.AccountType


interface SplitEntity : CommonField {
    /** 分录所属交易ID */
    val voucherId: Long

    /** 金额 */
    var amount: Int

    /** 借方或贷方 */
    var isDebit: Boolean

    /** 账户ID */
    var accountId: Long

    /** 账户名称 */
    var accountName: String

    /** 账户类型 */
    var accountType: AccountType
}