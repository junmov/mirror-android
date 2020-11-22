package cn.junmov.mirror.core.data.dao

import androidx.room.Dao
import androidx.room.Query
import cn.junmov.mirror.core.data.entity.Budget
import kotlinx.coroutines.flow.Flow
import java.time.YearMonth

@Dao
interface BudgetDao : BaseDao<Budget> {
    @Query("select * from budget where month_at = :month and account_id = :accountId")
    suspend fun findBudgetByAccountId(accountId: Long, month: YearMonth): Budget
    @Query("select * from budget where row_id = :id")
    fun flowBudget(id: Long): Flow<Budget>
    @Query("select * from budget where row_id = :id")
    suspend fun findById(id: Long): Budget
    @Query("select * from budget where month_at = :month and parent_id = :parentId")
    fun flowAllByParent(parentId: Long, month: YearMonth): Flow<List<Budget>>

}