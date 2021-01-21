package cn.junmov.mirror.sync.data

import cn.junmov.mirror.core.data.db.entity.*

data class TableData(
    var shouldSnapshot: Boolean = false,
    val accounts: List<Account> = emptyList(),
    val assets: List<Asset> = emptyList(),
    val assetLogs: List<AssetLog> = emptyList(),
    val debts: List<Debt> = emptyList(),
    val repays: List<Repay> = emptyList(),
    val todos: List<Todo> = emptyList(),
    val vouchers: List<Voucher> = emptyList(),
    val splits: List<Split> = emptyList(),
    val things: List<Thing> = emptyList()
)

