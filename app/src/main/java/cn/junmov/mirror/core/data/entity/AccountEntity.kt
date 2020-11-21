package cn.junmov.mirror.core.data.entity

import cn.junmov.mirror.core.data.AccountType

interface AccountEntity : CommonField {
    /** 账户名称 */
    var name: String

    /** 账户全名 */
    var fullName: String

    /** 账户类型 */
    val type: AccountType

    /** 账户排序依据 以交易次数为依据 */
    var sortKey: Int

    /** 上级账户 */
    val parentId: Long

    /** 账户余额 */
    var balance: Int

    /** 是否是叶子，叶子节点才会作为交易科目 */
    val isLeaf: Boolean

}