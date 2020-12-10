package cn.junmov.mirror.core.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
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
            ).addMigrations(MIGRATION_1_2).createFromAsset("database/mirror.db").build()
        }

        private val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL(
                    """CREATE TABLE IF NOT EXISTS `new_todo` (
                       `row_id` INTEGER NOT NULL, 
                       `summary` TEXT NOT NULL, 
                       `is_enabled` INTEGER NOT NULL, 
                       `run_at` TEXT NOT NULL, 
                       `period` TEXT NOT NULL, 
                       `done_times` INTEGER NOT NULL, 
                       `done_total` INTEGER NOT NULL, 
                       `create_at` TEXT NOT NULL, 
                       `modified_at` TEXT NOT NULL, 
                       `is_deleted` INTEGER NOT NULL, 
                       PRIMARY KEY(`row_id`))""".trimMargin()
                )
                database.execSQL(
                    """INSERT INTO new_todo(
                       `row_id`,`summary`,`is_enabled`,`run_at`,`period`,
                       `done_times`,`done_total`,`create_at`,`modified_at`,`is_deleted`) 
                       SELECT 
                       `row_id`,`summary`,`is_enabled`,`run_at`,`period`, 
                       `done_times`,`done_total`,`create_at`,`modified_at`,`is_deleted` 
                       FROM todo""".trimIndent()
                )
                database.execSQL("DROP TABLE todo")
                database.execSQL("ALTER TABLE new_todo RENAME TO todo")
            }
        }
    }
}