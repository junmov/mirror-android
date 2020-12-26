package cn.junmov.mirror.core.data.db.dao

import androidx.room.Dao
import androidx.room.Query
import cn.junmov.mirror.core.data.model.AccountType
import cn.junmov.mirror.core.data.db.entity.Account
import kotlinx.coroutines.flow.Flow

@Dao
interface AccountDao : BaseDao<Account> {

    @Query("select * from account where trad_able = 1 and is_deleted = 0 order by trade_count desc")
    fun flowAllTradAbleAccount(): Flow<List<Account>>

    @Query("select * from account where row_id in (:ids)")
    suspend fun listAllById(ids: List<Long>): List<Account>

    @Query("select * from account where parent_id = :firstId and is_deleted = 0")
    fun flowAllByParent(firstId: Long): Flow<List<Account>>

    @Query("select * from account where row_id = :id")
    fun flowAccount(id: Long): Flow<Account>

    @Query("select * from account where row_id = :id")
    suspend fun findById(id: Long): Account

    @Query("select * from account where trad_able = :tradAble and type in (:type) and is_deleted = 0")
    fun flowAllByTypeAndTradAble(tradAble: Boolean, vararg type: AccountType): Flow<List<Account>>

    @Query("select * from account where is_deleted= 0")
    suspend fun listAll(): List<Account>

}