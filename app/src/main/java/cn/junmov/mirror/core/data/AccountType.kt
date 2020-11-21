package cn.junmov.mirror.core.data

enum class AccountType(private val typeName: String) {
    /**
     * 流动性强的资产
     * 如现金、银行存款、支付宝余额、微信余额等
     */
    FUND("资金账户"),

    /**
     * 未来应收到现金或现金等价物的资产
     * 如借出款、押金、政府补贴、垫付等
     */
    RECEIVABLE("应收账户"),

    /**
     * 可产生被动收入的资产
     * 如基金、股票等
     */
    INVEST("投资账户"),

    /**
     * 未来应支付现金或现金等价物的负债
     * 如欠款等
     */
    PAYABLE("应付账户"),

    /**
     * 随时间增加而产生利息的负债
     * 如蚂蚁花呗、蚂蚁借呗、信用卡等
     */
    DEBT("债务账户"),

    /**
     * 日常消费产生的支出
     * 如餐饮、交通、医疗等
     */
    CONSUME("消费支出"),

    /**
     * 购买长期使用的物品所产生的支出
     * 如家具、家电、工具、设备、电子产品等
     */
    SUPPLIES("物资支出"),

    /**
     * 不应该或可避免的费用支出
     * 如利息、手续费、税费、维修费等
     */
    EXPENSE("费用支出"),

    /**
     * 付出时间、劳动或资产才能产生收益的收入
     * 如薪水、酬劳、售出账款等
     */
    ACTIVE("主动收入"),

    /**
     * 无需本人到场的就能产生收益的收入
     * 如投资、出租、收到的红包等
     */
    PASSIVE("被动收入");

    override fun toString(): String = this.typeName

    fun balanceInDebit(): Boolean {
        return when (this) {
            FUND, RECEIVABLE, INVEST, SUPPLIES,
            EXPENSE, CONSUME -> true
            DEBT, PAYABLE, ACTIVE, PASSIVE -> false
        }
    }

    fun hasNormalBalance(): Boolean {
        return when (this) {
            FUND, DEBT, PAYABLE, RECEIVABLE, INVEST -> true
            else -> false
        }
    }

    fun isAsset(): Boolean {
        return when (this) {
            FUND, RECEIVABLE, INVEST -> true
            else -> false
        }
    }

    fun isLiability(): Boolean {
        return when (this) {
            DEBT, PAYABLE -> true
            else -> false
        }
    }

    fun isIncome(): Boolean {
        return when (this) {
            ACTIVE, PASSIVE -> true
            else -> false
        }
    }

    fun isExpend(): Boolean {
        return when (this) {
            SUPPLIES, EXPENSE, CONSUME -> true
            else -> false
        }
    }

}