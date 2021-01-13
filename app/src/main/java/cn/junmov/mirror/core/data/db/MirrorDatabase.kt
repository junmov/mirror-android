package cn.junmov.mirror.core.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import cn.junmov.mirror.core.data.db.dao.*
import cn.junmov.mirror.core.data.db.entity.*

@Database(
    entities = [
        Account::class, Voucher::class, Split::class,
        Thing::class, Trade::class,
        Debt::class, Repay::class,
        Asset::class, AssetLog::class,
        Todo::class
    ],
    version = Scheme.DATABASE_VERSION,
    exportSchema = true,
)
@TypeConverters(DataTypeConverters::class)
abstract class MirrorDatabase : RoomDatabase() {
    abstract fun accountDao(): AccountDao
    abstract fun thingDao(): ThingDao
    abstract fun voucherDao(): VoucherDao
    abstract fun tradeDao(): TradeDao
    abstract fun auditDao(): AuditDao
    abstract fun debtDao(): DebtDao
    abstract fun assetDao(): AssetDao
    abstract fun toDoDao(): TodoDao
    abstract fun mineDao(): MineDao

    companion object {
        @Volatile
        private var instance: MirrorDatabase? = null

        fun getInstance(context: Context): MirrorDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): MirrorDatabase {
            return Room.databaseBuilder(
                context.applicationContext, MirrorDatabase::class.java, Scheme.DATABASE_NAME
            ).build()
        }

    }
}