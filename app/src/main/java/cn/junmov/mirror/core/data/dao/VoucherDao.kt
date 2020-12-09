package cn.junmov.mirror.core.data.dao

import androidx.paging.PagingSource
import androidx.room.*
import cn.junmov.mirror.core.data.entity.Voucher
import cn.junmov.mirror.core.data.entity.Split
import cn.junmov.mirror.core.data.model.VoucherAndSplits
import cn.junmov.mirror.voucher.data.ItemVoucher
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

@Dao
interface VoucherDao : BaseDao<Voucher> {

    @Transaction
    @Query("SELECT * FROM voucher WHERE row_id = :id")
    fun flowVoucherInfo(id: Long): Flow<VoucherAndSplits>

    @Query("select row_id, summary, thing_name, date_at, time_at, profit, is_audited from voucher where is_audited = :audited and is_deleted = 0 order by date_at desc, time_at desc")
    fun pagingVoucher(audited: Boolean): PagingSource<Int, ItemVoucher>

    @Query("select * from split where voucher_id = :voucherId and is_deleted = 0")
    fun flowAllSplitByVoucher(voucherId: Long): Flow<List<Split>>

    @Insert
    suspend fun insertSplit(vararg split: Split)

    @Update
    suspend fun updateSplit(split: Split)

    @Query("SELECT * FROM voucher WHERE row_id = :id")
    fun flowVoucher(id: Long): Flow<Voucher>

    @Transaction
    suspend fun copyTransaction(voucher: Voucher, splits: List<Split>) {
        insert(voucher)
        insertSplit(*splits.toTypedArray())
    }

    @Query(
        """select row_id, summary, thing_name, date_at, time_at, profit, is_audited 
        from voucher where date_at between :start and :end order by date_at desc, time_at desc"""
    )
    fun flowAllVoucherBetween(start: LocalDate, end: LocalDate): Flow<List<ItemVoucher>>

}