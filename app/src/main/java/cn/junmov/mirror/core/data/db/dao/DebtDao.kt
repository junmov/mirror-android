package cn.junmov.mirror.core.data.db.dao

import androidx.room.*
import cn.junmov.mirror.core.data.db.entity.Account
import cn.junmov.mirror.core.data.db.entity.Debt
import cn.junmov.mirror.core.data.db.entity.Repay
import cn.junmov.mirror.core.data.db.entity.Voucher
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

    @Transaction
    suspend fun createAgingDebtTransaction(
        debt: Debt,
        repays: List<Repay>,
        voucher: Voucher?,
        accounts: List<Account>
    ) {
        insert(debt)
        insertAllRepay(repays)
        voucher?.let {
            insertVoucher(it)
            updateAccounts(accounts)
        }
    }

    @Update
    suspend fun updateAccounts(accounts: List<Account>)

    @Insert
    suspend fun insertVoucher(voucher: Voucher)

    @Query("select * from debt where row_id = :id")
    fun flowDebt(id: Long): Flow<Debt>

    @Query("select * from repay where debt_id = :debtId and is_settled = 0")
    suspend fun listAllNoSettledRepay(debtId: Long): List<Repay>

    @Update
    suspend fun updateRepay(repay: Repay)

    @Query("select * from debt where is_deleted = 0 order by borrow_at desc")
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

    @Query("select * from repay where debt_id = :debtId and is_deleted = 0")
    fun flowDebtRepays(debtId: Long): Flow<List<Repay>>

    @Query("select * from repay where date_at = :dateAt and is_settled = 0 and is_deleted = 0")
    fun flowRepaysByDate(dateAt: LocalDate): Flow<List<Repay>>

}