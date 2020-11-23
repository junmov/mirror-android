package cn.junmov.mirror.core.data.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import cn.junmov.mirror.core.data.entity.Voucher

@Dao
interface StatisticsDao {

    @Query(" select * from voucher where row_id in (select voucher_id from trade where account_id = :accountId)")
    fun pagingAccountTrading(accountId: Long): PagingSource<Int, Voucher>
}