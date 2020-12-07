package cn.junmov.mirror.core.data.dao

import androidx.paging.PagingSource
import androidx.room.*
import cn.junmov.mirror.core.data.entity.Bill
import cn.junmov.mirror.core.data.entity.Debt
import cn.junmov.mirror.core.data.entity.Repay
import cn.junmov.mirror.core.data.model.DebtInfo
import kotlinx.coroutines.flow.Flow

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
    suspend fun createAgingDebtTransaction(
        debt: Debt, repays: List<Repay>, bills: List<Bill>
    ) {
        insert(debt)
        insertAllRepay(repays)
        saveBills(bills)
    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveBills(bills: List<Bill>)

    @Query("select * from debt where row_id = :id")
    fun flowAgingDebt(id: Long): Flow<Debt>

    @Transaction
    suspend fun stopLossTransaction(
        debt: Debt, repays: List<Repay>, repay: Repay, bills: List<Bill>
    ) {
        updateAllRepay(repays)
        insertRepay(repay)
        update(debt)
        updateAllBill(bills)
    }

    @Update
    suspend fun updateAllBill(bills: List<Bill>)

    @Query("select * from repay where debt_id = :debtId and is_settled = 0")
    suspend fun listAllNoSettledRepay(debtId: Long): List<Repay>

    @Query("select * from repay where row_id = :id")
    fun flowRepay(id: Long): Flow<Repay>

    @Update
    suspend fun updateRepay(repay: Repay)

    @Query("select * from debt where is_deleted = 0 order by start_at desc")
    fun flowAllDebt(): Flow<List<Debt>>

    @Transaction
    suspend fun updateRepayTransaction(repay: Repay, bill: Bill) {
        updateRepay(repay)
        updateBill(bill)
    }

    @Update
    suspend fun updateBill(bill: Bill)
}