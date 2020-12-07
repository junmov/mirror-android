package cn.junmov.mirror.core.data.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import androidx.room.RewriteQueriesToDropUnusedColumns
import androidx.room.Transaction
import cn.junmov.mirror.core.data.entity.Thing
import cn.junmov.mirror.voucher.data.ItemVoucher
import kotlinx.coroutines.flow.Flow
import java.time.LocalDateTime

@Dao
interface ThingDao : BaseDao<Thing> {

    @Query("SELECT * FROM thing where is_deleted = 0")
    fun flowAllThing(): Flow<List<Thing>>

    @Query("select row_id, summary, thing_name, date_at, time_at, profit, is_audited from voucher where is_deleted = 0 and thing_id = :thingId order by date_at desc, time_at desc")
    fun pagingVoucherByThing(thingId: Long): PagingSource<Int, ItemVoucher>

    @Transaction
    suspend fun renameTransaction(eventId: Long, name: String, modifiedAt: LocalDateTime) {
        renameInThing(eventId, name, modifiedAt)
        renameInVoucher(eventId, name, modifiedAt)
    }

    @Query("update voucher set thing_name = :name, modified_at = :modifiedAt where thing_id = :thingId")
    suspend fun renameInVoucher(thingId: Long, name: String, modifiedAt: LocalDateTime)

    @Query("update thing set name = :name, modified_at = :modifiedAt where row_id = :thingId")
    suspend fun renameInThing(thingId: Long, name: String, modifiedAt: LocalDateTime)

}