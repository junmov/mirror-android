package cn.junmov.mirror.core.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import cn.junmov.mirror.core.data.db.dao.*
import cn.junmov.mirror.core.data.db.entity.*
import cn.junmov.mirror.core.data.db.view.ItemVoucher

@Database(
    entities = [
        Account::class,
        Voucher::class,
        Debt::class, Repay::class,
        Asset::class, AssetLog::class,
    ],
    views = [ItemVoucher::class],
    version = Scheme.DATABASE_VERSION,
    exportSchema = true,
)
@TypeConverters(DataTypeConverters::class)
abstract class MirrorDatabase : RoomDatabase() {
    abstract fun accountDao(): AccountDao
    abstract fun voucherDao(): VoucherDao
    abstract fun debtDao(): DebtDao
    abstract fun assetDao(): AssetDao
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
            ).fallbackToDestructiveMigration().build()
        }

    }
}