package cn.junmov.mirror.core.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Transaction
import androidx.room.Update
import cn.junmov.mirror.core.data.db.entity.Account
import cn.junmov.mirror.core.data.db.entity.Trade
import cn.junmov.mirror.core.data.db.entity.Voucher

@Dao
interface AuditDao {
    @Transaction
    suspend fun auditTransaction(
        voucher: Voucher, accounts: List<Account>, trades: List<Trade>
    ) {
        updateVoucher(voucher)
        updateAccounts(accounts)
        insertTrades(trades)
    }

    @Update
    suspend fun updateVoucher(voucher: Voucher)


    @Insert
    suspend fun insertTrades(trades: List<Trade>)

    @Update
    suspend fun updateAccounts(accounts: List<Account>)

}
