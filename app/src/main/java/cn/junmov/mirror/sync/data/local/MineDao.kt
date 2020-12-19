package cn.junmov.mirror.sync.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import cn.junmov.mirror.core.data.entity.*
import java.time.LocalDateTime

@Dao
interface MineDao {
    @Query("select * from account where modified_at > :modified")
    suspend fun listAccount(modified: LocalDateTime): List<Account>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAccount(accounts: List<Account>)

    @Query("select * from asset where modified_at > :modified")
    suspend fun listAsset(modified: LocalDateTime): List<Asset>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAsset(assets: List<Asset>)

    @Query("select * from asset_log where modified_at > :modified")
    suspend fun listAssetLog(modified: LocalDateTime): List<AssetLog>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAssetLog(logs: List<AssetLog>)

    @Query("select * from balance where modified_at > :modified")
    suspend fun listBalance(modified: LocalDateTime): List<Balance>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBalance(balances: List<Balance>)

    @Query("select * from bill where modified_at > :modified")
    suspend fun listBill(modified: LocalDateTime): List<Bill>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBill(bills: List<Bill>)

    @Query("select * from debt where modified_at > :modified")
    suspend fun listDebt(modified: LocalDateTime): List<Debt>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDebt(debts: List<Debt>)

    @Query("select * from repay where modified_at > :modified")
    suspend fun listRepay(modified: LocalDateTime): List<Repay>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRepay(repays: List<Repay>)

    @Query("select * from split where modified_at > :modified")
    suspend fun listSplit(modified: LocalDateTime): List<Split>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSplits(splits: List<Split>)

    @Query("select * from thing where modified_at > :modified")
    suspend fun listThing(modified: LocalDateTime): List<Thing>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertThing(things: List<Thing>)

    @Query("select * from todo where modified_at > :modified")
    suspend fun listTodo(modified: LocalDateTime): List<Todo>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTodo(toDos: List<Todo>)

    @Query("select * from trade where modified_at > :modified")
    suspend fun listTrade(modified: LocalDateTime): List<Trade>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTrade(trades: List<Trade>)

    @Query("select * from voucher where modified_at > :modified")
    suspend fun listVoucher(modified: LocalDateTime): List<Voucher>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertVoucher(vouchers: List<Voucher>)
}