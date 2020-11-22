package cn.junmov.mirror.core.data.dao

import androidx.room.*
import cn.junmov.mirror.core.data.entity.*

@Dao
interface AuditDao {
    @Transaction
    suspend fun auditTransaction(
        voucher: Voucher, splits: List<Split>, accounts: List<Account>,
        trades: List<Trade>, budgets: List<Budget>
    ) {
        insertVoucher(voucher)
        insertSplits(splits)
        updateAccounts(accounts)
        insertTrades(trades)
        updateBudgets(budgets)
    }

    @Update
    suspend fun updateBudgets(budgets: List<Budget>)

    @Insert
    suspend fun insertSplits(splits: List<Split>)

    @Insert
    suspend fun insertTrades(trades: List<Trade>)

    @Update
    suspend fun updateAccounts(accounts: List<Account>)

    @Insert
    suspend fun insertVoucher(voucher: Voucher)
}
