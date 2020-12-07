package cn.junmov.mirror.core.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import cn.junmov.mirror.core.data.dao.*
import cn.junmov.mirror.core.data.entity.*

@Database(
    entities = [
        Account::class, Balance::class, Voucher::class, Split::class,
        Thing::class, Trade::class,
        Debt::class, Repay::class, Bill::class,
        Asset::class, AssetLog::class,
        Todo::class
    ],
    version = Scheme.DATABASE_VERSION,
    exportSchema = false,
)
@TypeConverters(DataTypeConverters::class)
abstract class MirrorDatabase : RoomDatabase() {
    abstract fun accountDao(): AccountDao
    abstract fun balanceDao(): BalanceDao
    abstract fun thingDao(): ThingDao
    abstract fun voucherDao(): VoucherDao
    abstract fun tradeDao(): TradeDao
    abstract fun auditDao(): AuditDao
    abstract fun debtDao(): DebtDao
    abstract fun billDao(): BillDao
    abstract fun assetDao(): AssetDao
    abstract fun toDoDao(): TodoDao
}