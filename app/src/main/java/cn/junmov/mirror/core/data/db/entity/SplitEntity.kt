package cn.junmov.mirror.core.data.db.entity

import cn.junmov.mirror.core.data.model.AccountType


interface SplitEntity : CommonField {
    /** 分录所属交易ID */
    val voucherId: Long

    /** 金额 */
    var amount: Int

    /** 借方或贷方 */
    var debit: Boolean

    /** 账户ID */
    var accountId: Long

    /** 账户名称 */
    var accountName: String

    /** 账户类型 */
    var accountType: AccountType

    /** 上级账户ID */
    var accountParentId: Long
}