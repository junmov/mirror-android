package cn.junmov.mirror.core.data.model

import cn.junmov.mirror.core.data.db.entity.*

data class TableData(
    val accounts: List<Account> = emptyList(),
    val assets: List<Asset> = emptyList(),
    val assetLogs: List<AssetLog> = emptyList(),
    val debts: List<Debt> = emptyList(),
    val repays: List<Repay> = emptyList(),
    val vouchers: List<Voucher> = emptyList(),
)

