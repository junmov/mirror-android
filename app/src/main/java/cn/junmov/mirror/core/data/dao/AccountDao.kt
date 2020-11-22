package cn.junmov.mirror.core.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import cn.junmov.mirror.account.data.AccountWithChildren
import cn.junmov.mirror.core.data.AccountType
import cn.junmov.mirror.core.data.entity.Account
import cn.junmov.mirror.core.data.entity.Budget
import kotlinx.coroutines.flow.Flow

@Dao
interface AccountDao : BaseDao<Account> {

    @Query("select * from account where is_leaf = :isLeaf and is_deleted = 0 order by type")
    fun flowAllByLeaf(isLeaf: Boolean): Flow<List<Account>>

    @Transaction
    suspend fun createAccountTransaction(account: Account, budget: Budget?) {
        insert(account)
        budget?.let { insertBudget(it) }
    }

    @Insert
    suspend fun insertBudget(budget: Budget)

    @Transaction
    @Query("select * from account where row_id = :id and is_deleted = 0 ")
    fun flowAccountWithChildren(id: Long): Flow<AccountWithChildren>

    @Query("select * from account where row_id in (:ids) and is_deleted = 0")
    suspend fun findAllById(ids: List<Long>): List<Account>

    @Query("select * from account where type in (:type) and is_leaf = :isLeaf and is_deleted = 0 order by type")
    fun flowAllByTypeAndLeaf(isLeaf: Boolean, vararg type: AccountType): Flow<List<Account>>
}