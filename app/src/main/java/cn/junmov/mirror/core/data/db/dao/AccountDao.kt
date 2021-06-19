package cn.junmov.mirror.core.data.db.dao

import androidx.room.Dao
import androidx.room.Query
import cn.junmov.mirror.core.data.db.entity.Account
import cn.junmov.mirror.core.data.model.AccountType
import kotlinx.coroutines.flow.Flow

@Dao
interface AccountDao : BaseDao<Account> {

    @Query("select * from account where row_id = :id")
    fun flowAccount(id: Long): Flow<Account>

    @Query("select * from account where is_deleted = 0 and type in (:type)")
    fun flowAllByType(vararg type: AccountType): Flow<List<Account>>

    @Query("select * from account where is_deleted = 0 and type in (:type) and budget != 0")
    fun flowAllBudget(vararg type: AccountType): Flow<List<Account>>

    @Query("select * from account where row_id = :id")
    suspend fun findById(id: Long): Account


    @Query("select * from account where is_deleted = 0 order by trade_count desc")
    fun flowAllAccountTradeCountDesc(): Flow<List<Account>>

    @Query("select * from account where is_deleted = 0 order by type, name")
    fun flowAll(): Flow<List<Account>>

    @Query("select * from account where is_deleted = 0 and budget > 0")
    suspend fun listAllBudget(): List<Account>

}