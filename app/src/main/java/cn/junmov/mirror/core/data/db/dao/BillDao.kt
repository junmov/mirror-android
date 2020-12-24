package cn.junmov.mirror.core.data.db.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import cn.junmov.mirror.core.data.db.entity.Bill
import java.time.LocalDate

@Dao
interface BillDao : BaseDao<Bill> {

    @Query("select * from bill where date_at = :date")
    suspend fun findByDateAt(date: LocalDate): Bill

    @Query("select * from bill where row_id = :id")
    suspend fun findById(id: Long): Bill

    @Query("select * from bill where date_at between :start and :end")
    suspend fun listAllBetween(start: LocalDate, end: LocalDate): List<Bill>

    @Query("select * from bill where date_at in (:dates)")
    suspend fun listAll(dates: List<LocalDate>): List<Bill>

    @Query("select * from bill where is_settled = :settled order by date_at")
    fun pagingBillBySettled(settled: Boolean): PagingSource<Int, Bill>

}