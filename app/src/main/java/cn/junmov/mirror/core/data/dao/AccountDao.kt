package cn.junmov.mirror.core.data.dao

import androidx.room.Dao
import androidx.room.Query
import cn.junmov.mirror.core.data.AccountType
import cn.junmov.mirror.core.data.entity.Account
import cn.junmov.mirror.core.data.model.Category
import cn.junmov.mirror.core.data.model.Wallet
import kotlinx.coroutines.flow.Flow

@Dao
interface AccountDao : BaseDao<Account> {

    @Query("select * from account where trad_able = 1 and is_deleted = 0 order by trade_count desc")
    fun flowAllTradAbleAccount(): Flow<List<Account>>

    @Query("select * from account where row_id in (:ids)")
    suspend fun findAllById(ids: List<Long>): List<Account>

    @Query("select * from account where type in (:types) and is_deleted = 0")
    fun flowAllWallet(vararg types: AccountType): Flow<List<Wallet>>

    @Query("select * from account where row_id = :walletId")
    fun flowWallet(walletId: Long): Flow<Wallet>

    @Query("select * from account where trad_able = 0 and type in (:types) and is_deleted = 0")
    fun flowAllFirstBudget(vararg types: AccountType): Flow<List<Category>>

    @Query("select * from account where parent_id = :firstId and is_deleted = 0")
    fun flowAllSecondaryBudget(firstId: Long): Flow<List<Category>>

    @Query("select * from account where row_id = :categoryId")
    fun flowCategory(categoryId: Long): Flow<Category>

    @Query("select * from account where row_id = :id")
    suspend fun findById(id: Long): Category

    @Query("select * from account where type in (:type) and is_deleted = 0")
    fun flowAllDebtAccount(vararg type: AccountType): Flow<List<Account>>

}