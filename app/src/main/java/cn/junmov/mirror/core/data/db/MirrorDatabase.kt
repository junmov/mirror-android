package cn.junmov.mirror.core.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import cn.junmov.mirror.core.data.db.dao.*
import cn.junmov.mirror.core.data.db.entity.*

@Database(
    entities = [
        Account::class, Voucher::class, Split::class,
        Thing::class,
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
            ).addMigrations(MIGRATION_1_2, MIGRATION_2_3, MIGRATION_3_4, MIGRATION_4_5).build()
        }

        private val MIGRATION_4_5 = object : Migration(4, 5) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL(
                    """CREATE TABLE IF NOT EXISTS `new_asset_log` (
                    `row_id` INTEGER NOT NULL, 
                    `asset_id` INTEGER NOT NULL, 
                    `is_buy` INTEGER NOT NULL, 
                    `count` INTEGER NOT NULL, 
                    `amount` INTEGER NOT NULL, 
                    `create_at` TEXT NOT NULL, 
                    `modified_at` TEXT NOT NULL, 
                    `is_deleted` INTEGER NOT NULL, 
                    PRIMARY KEY(`row_id`))""".trimMargin()
                )
                database.execSQL("""
                    INSERT INTO `new_asset_log`(
                       `row_id`, `asset_id`, `is_buy`, `count`, `amount`, `create_at`, `modified_at`, `is_deleted`
                    )
                    SELECT 
                       `row_id`, `asset_id`, `is_buy`, `count`, `amount`, `create_at`, `modified_at`, `is_deleted`
                    FROM `asset_log`   
                """.trimIndent())

                database.execSQL("DROP TABLE `asset_log`")
                database.execSQL("ALTER TABLE `new_asset_log` RENAME TO `asset_log`")
            }
        }

        private val MIGRATION_3_4 = object : Migration(3, 4) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE `voucher` ADD `type` TEXT DEFAULT '' NOT NULL ")
                database.execSQL("UPDATE `voucher` SET `type` = 'INCOME' WHERE `profit` > 0 ")
                database.execSQL("UPDATE `voucher` SET `type` = 'EXPEND' WHERE `profit` < 0 ")
                database.execSQL("UPDATE `voucher` SET `type` = 'TRANSFER' WHERE `profit` = 0 ")
            }
        }

        private val MIGRATION_2_3 = object : Migration(2, 3) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("DROP TABLE `trade`")
            }
        }

        private val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL(
                    """CREATE TABLE IF NOT EXISTS `new_debt` (
                    `row_id` INTEGER NOT NULL, 
                    `summary` TEXT NOT NULL, 
                    `account_id` INTEGER NOT NULL, 
                    `start_at` TEXT NOT NULL, 
                    `capital` INTEGER NOT NULL, 
                    `capital_repay` INTEGER NOT NULL, 
                    `count` INTEGER NOT NULL, 
                    `count_repay` INTEGER NOT NULL, 
                    `interest_repay` INTEGER NOT NULL, 
                    `is_settled` INTEGER NOT NULL, 
                    `create_at` TEXT NOT NULL, 
                    `modified_at` TEXT NOT NULL, 
                    `is_deleted` INTEGER NOT NULL, 
                    PRIMARY KEY(`row_id`))""".trimMargin()
                )
                database.execSQL(
                    """
                    INSERT INTO `new_debt` ( 
                        `row_id`, `summary`, `account_id`, `start_at`, `capital`, `capital_repay`, `count`, `count_repay`, `interest_repay`, `is_settled`, `create_at`, `modified_at`, `is_deleted`
                    ) 
                    SELECT 
                        `row_id`, `summary`, `account_id`, `start_at`, `capital`, `capital_repay`, `count`, `count_repay`, `interest_repay`, `is_settled`, `create_at`, `modified_at`, `is_deleted` 
                    FROM `debt`
                    """.trimIndent()
                )
                database.execSQL("DROP TABLE `debt`")
                database.execSQL("ALTER TABLE `new_debt` RENAME TO `debt`")
            }
        }
    }
}