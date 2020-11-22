package cn.junmov.mirror.core.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import cn.junmov.mirror.core.data.dao.*
import cn.junmov.mirror.core.data.entity.*

@Database(
    entities = [
        Account::class, Budget::class, Split::class, Thing::class, Trade::class, Voucher::class
    ],
    version = Scheme.DATABASE_VERSION,
    exportSchema = false,
)
@TypeConverters(DataTypeConverters::class)
abstract class MirrorDatabase : RoomDatabase() {
    abstract fun accountDao(): AccountDao
    abstract fun auditDao(): AuditDao
    abstract fun budgetDao(): BudgetDao
    abstract fun thingDao(): ThingDao
    abstract fun voucherDao(): VoucherDao
    abstract fun tradeDao(): TradeDao

}