package cn.junmov.mirror.core.data.db.entity

import cn.junmov.mirror.core.data.model.AccountType

/**
 * 账户分为钱包账户和分类账户
 * 钱包账户有余额，余额为[base]，[inflow]为余额流入，[outflow]为余额流出
 * 分类账户有预算，预算总额为[base]+[inflow], [inflow]为上期预算结转或其他预算转入，[outflow]为已使用预算
 */
interface AccountEntity : CommonField {

    /** 账户名称 */
    val name: String

    /** 账户全名 */
    val fullName: String

    /** 账户类型 */
    val type: AccountType

    /** 上级账户 */
    val parentId: Long

    /** 是否可交易*/
    val tradAble: Boolean

    /** 账户参与的交易数 */
    var tradeCount: Int

    /** 余额或基础预算 */
    var base: Int

    /** 余额流入或预算增量 */
    var inflow: Int

    /** 余额流出或使用预算 */
    var outflow: Int

}