package cn.junmov.mirror.core.data.db.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import cn.junmov.mirror.core.data.db.entity.Account
import cn.junmov.mirror.core.data.db.entity.Balance

@Dao
interface BalanceDao : BaseDao<Balance> {

    @Query("select * from balance where account_id = :id and is_deleted = 0")
    suspend fun findAllByWallet(id: Long): List<Balance>

    @Transaction
    suspend fun updateBalanceTransaction(account: Account, balances: List<Balance>) {
        updateAccount(account)
        update(*balances.toTypedArray())
    }

    @Update
    suspend fun updateAccount(account: Account)

}