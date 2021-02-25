package cn.junmov.mirror.core.data.db.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import cn.junmov.mirror.budget.data.Budget
import cn.junmov.mirror.core.data.model.AccountType
import cn.junmov.mirror.core.data.db.entity.Account
import cn.junmov.mirror.voucher.data.ItemVoucher
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

    @Query("""select row_id, name, type, base, inflow, outflow from account where trad_able = 0 and is_deleted = 0""")
    fun flowFirstBudget(): Flow<List<Budget>>

    @Query("""select row_id, name, type, base, inflow, outflow from account where parent_id = :firstId and is_deleted = 0""")
    fun flowSecondaryBudget(firstId: Long): Flow<List<Budget>>

    @Query(
        """
        select v.row_id, v.is_audited, v.summary, v.date_at, v.time_at, v.profit, v.thing_name 
        from voucher v left join split s on s.voucher_id = v.row_id
        where s.account_id = :accountId and v.is_audited = 1 and v.is_deleted = 0 and s.is_deleted = 0
        group by v.row_id
        order by v.date_at desc,v.time_at desc
        """
    )
    fun pagingAccountTrading(accountId: Long): PagingSource<Int, ItemVoucher>

    @Query(
        """
        select v.row_id, v.is_audited, v.summary, v.date_at, v.time_at, v.profit, v.thing_name 
        from voucher v left join split s on s.voucher_id = v.row_id
        where s.account_id = :accountId and v.is_audited = 1 and v.is_deleted = 0 and s.is_deleted = 0
        group by v.row_id
        order by v.date_at desc, v.time_at desc
        limit :limit
        """
    )
    fun flowAccountTradeLimit(accountId: Long, limit: Int): Flow<List<ItemVoucher>>
}