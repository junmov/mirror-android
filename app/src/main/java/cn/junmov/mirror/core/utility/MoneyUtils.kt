package cn.junmov.mirror.core.utility

import cn.junmov.mirror.core.data.model.AccountType
import java.math.BigDecimal
import java.util.regex.Pattern

object MoneyUtils {

    private val rate = BigDecimal("100")

    fun centToYuan(cent: Int): String {
        return BigDecimal.valueOf(cent.toLong()).divide(rate).toString()
    }

    fun yuanToCent(yuan: String): Int {
        return BigDecimal(yuan).multiply(rate).toInt()
    }

    /**
     * 计算不同类型的账户因在借贷方向上的不同导致的账户余额变化量
     */
    fun computeBalanceDelta(type: AccountType, isDebit: Boolean, amount: Int): Int {
        /*
         * 1. 如果余额在借方向，是借方，那么相加
         * 2. 如果余额在借方向，是贷方，那么相减
         * 3. 如果余额在贷方向，是借方，那么相减
         * 4. 如果余额在贷方向，是贷方，那么相加
         */
        return if (type.balanceInDebit() == isDebit) amount else -amount

    }

    fun isFormat(str: String): Boolean {
        val reg = "^(0|[1-9]\\d*)(\\.\\d{1,2})?$"
        val patten = Pattern.compile(reg)
        val matcher = patten.matcher(str)
        return matcher.matches()
    }

}