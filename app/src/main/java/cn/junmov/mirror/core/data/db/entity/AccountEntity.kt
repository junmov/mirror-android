package cn.junmov.mirror.core.data.db.entity

import cn.junmov.mirror.core.data.model.AccountType

/**
 * 账户分为钱包账户和分类账户
 * 钱包账户有余额，余额为[balance]，[debit]为余额流入，[credit]为余额流出
 * 分类账户有预算，预算总额为[balance]+[debit], [debit]为上期预算结转或其他预算转入，[credit]为已使用预算
 */
interface AccountEntity : CommonField {

    /** 账户名称 */
    val name: String

    /** 账户类型 */
    val type: AccountType

    /** 余额 */
    var balance: Int

    /** 预算 */
    var budget: Int

    /** 借方合计 */
    var debit: Int

    /** 贷方合计 */
    var credit: Int

    var tradeCount: Int
}