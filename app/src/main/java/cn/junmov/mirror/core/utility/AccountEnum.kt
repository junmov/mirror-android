package cn.junmov.mirror.core.utility

import cn.junmov.mirror.core.data.model.AccountType

enum class AccountEnum(val id: Long, val fullName: String, val type: AccountType) {
    NONE(0, "不存在的账户", AccountType.PAYABLE),
    FUND(782808877792284679L, "基金理财", AccountType.INVEST),
    HUA_BEI(782808877792284685L, "蚂蚁花呗", AccountType.PAYABLE),
    JIE_BEI(782808877792284686L, "蚂蚁借呗", AccountType.PAYABLE),
    WANG_SHANG_DAI(782808877792284687L, "网商贷", AccountType.PAYABLE),
    INTEREST(782808877792284737L, "其他支出:利息费用", AccountType.EXPENSE),
    CATERING(782808877792284696L, "餐饮美食:饮食服务", AccountType.EXPENSE),
    QI_TA_ZHI_CHU(782808877792284733L, "其他支出", AccountType.EXPENSE)
    ;
}