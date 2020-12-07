package cn.junmov.mirror.core.data.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import androidx.room.RewriteQueriesToDropUnusedColumns
import cn.junmov.mirror.core.data.entity.Voucher
import cn.junmov.mirror.voucher.data.ItemVoucher
import kotlinx.coroutines.flow.Flow

@Dao
interface TradeDao {
    @Query(
        """
        select v.row_id, v.is_audited, v.summary, v.date_at, v.time_at, t.amount as profit, v.thing_name 
        from trade t left join voucher v on t.voucher_id = v.row_id
        where t.account_id = :accountId and v.is_audited = 1 and t.is_deleted = 0
        order by v.date_at desc,time_at desc
        limit :limit
    """
    )
    fun flowAccountTradeLimit(accountId: Long, limit: Int): Flow<List<ItemVoucher>>

    @Query(" select row_id, summary, thing_name, date_at, time_at, profit, is_audited from voucher where row_id in (select voucher_id from trade where account_id = :accountId)")
    fun pagingAccountTrading(accountId: Long): PagingSource<Int, ItemVoucher>
}