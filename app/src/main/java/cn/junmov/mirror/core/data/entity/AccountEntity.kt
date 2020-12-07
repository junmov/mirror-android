package cn.junmov.mirror.core.data.entity

import cn.junmov.mirror.core.data.AccountType

interface AccountEntity : CommonField {

    /** 账户名称 */
    val name: String

    /** 账户全名 */
    val fullName: String

    /** 账户类型 */
    val type: AccountType

    /** 上级账户 */
    val parentId: Long

    /** 是否是叶子，叶子节点才会作为交易科目 */
    val tradAble: Boolean

    /** 账户排序依据 以交易次数为依据 */
    var tradeCount: Int

    /** 余额或基础预算 */
    var base: Int

    /** 余额流入或预算增量 */
    var inflow: Int

    /** 余额流出或使用预算 */
    var outflow: Int

}