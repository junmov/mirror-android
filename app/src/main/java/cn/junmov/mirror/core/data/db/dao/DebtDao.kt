package cn.junmov.mirror.core.data.db.dao

import androidx.paging.PagingSource
import androidx.room.*
import cn.junmov.mirror.core.data.db.entity.Debt
import cn.junmov.mirror.core.data.db.entity.Repay
import cn.junmov.mirror.core.data.model.DebtInfo
import cn.junmov.mirror.debt.data.DateRepay
import cn.junmov.mirror.debt.data.RepayAndDebt
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

@Dao
interface DebtDao : BaseDao<Debt> {

    @Insert
    suspend fun insertRepay(repay: Repay)

    @Update
    suspend fun updateAllRepay(repays: List<Repay>)

    @Insert
    suspend fun insertAllRepay(repays: List<Repay>)

    @Query("select * from debt ORDER BY is_settled, create_at DESC ")
    fun pagingAgingDebt(): PagingSource<Int, Debt>

    @Transaction
    @Query("select * from debt where row_id = :id")
    fun flowAgingDebtInfo(id: Long): Flow<DebtInfo>

    @Transaction
    suspend fun createAgingDebtTransaction(debt: Debt, repays: List<Repay>) {
        insert(debt)
        insertAllRepay(repays)
    }

    @Query("select * from debt where row_id = :id")
    fun flowAgingDebt(id: Long): Flow<Debt>

    @Transaction
    suspend fun stopLossTransaction(debt: Debt, repays: List<Repay>, repay: Repay) {
        updateAllRepay(repays)
        insertRepay(repay)
        update(debt)
    }

    @Query("select * from repay where debt_id = :debtId and is_settled = 0")
    suspend fun listAllNoSettledRepay(debtId: Long): List<Repay>

    @Query("select * from repay where row_id = :id")
    fun flowRepay(id: Long): Flow<Repay>

    @Update
    suspend fun updateRepay(repay: Repay)

    @Query("select * from debt where is_deleted = 0 order by start_at desc")
    fun flowAllDebt(): Flow<List<Debt>>

    @Transaction
    @Query("select * from repay where date_at = :dateAt")
    suspend fun findAllRepayAndDebtByDate(dateAt: LocalDate): List<RepayAndDebt>

    @Transaction
    suspend fun payDateRepayTransaction(debts: List<Debt>, repays: List<Repay>) {
        update(*debts.toTypedArray())
        updateAllRepay(repays)
    }

    @Query(
        """select r.date_at, sum(r.capital) as capital, sum(r.interest) as interest 
        from repay r where r.is_settled = :settled and r.is_deleted = 0 
        group by r.date_at order by r.date_at"""
    )
    fun flowDateRepay(settled: Boolean): Flow<List<DateRepay>>
}