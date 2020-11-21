package cn.junmov.mirror.core.data.dao

import androidx.room.Dao
import androidx.room.Query
import cn.junmov.mirror.core.data.entity.Budget
import java.time.YearMonth

@Dao
interface BudgetDao : BaseDao<Budget> {
    @Query("select * from budget where month_at = :month and account_id = :accountId")
    suspend fun findBudgetByAccountId(accountId: Long, month: YearMonth): Budget
}