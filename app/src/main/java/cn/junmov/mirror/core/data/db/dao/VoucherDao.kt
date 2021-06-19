package cn.junmov.mirror.core.data.db.dao

import androidx.paging.PagingSource
import androidx.room.*
import cn.junmov.mirror.core.data.db.entity.Account
import cn.junmov.mirror.core.data.db.entity.Voucher
import cn.junmov.mirror.core.data.db.view.ItemVoucher
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

@Dao
interface VoucherDao : BaseDao<Voucher> {

    @Query("select * from item_voucher order by date_at desc, time_at desc")
    fun pagingVoucher(): PagingSource<Int, ItemVoucher>

    @Query("SELECT * FROM voucher WHERE row_id = :id")
    fun flowVoucher(id: Long): Flow<Voucher>

    @Query("select * from item_voucher where date_at between :start and :end order by date_at desc, time_at desc")
    fun flowAllVoucherBetween(start: LocalDate, end: LocalDate): Flow<List<ItemVoucher>>

    @Transaction
    suspend fun submitVoucherTransaction(
        voucher: Voucher, debit: Account, credit: Account, isNew: Boolean = true
    ) {
        if (isNew) {
            insert(voucher)
        } else {
            update(voucher)
        }
        updateAccounts(debit, credit)
    }

    @Update
    suspend fun updateAccounts(vararg account: Account)

    @Query("select * from item_voucher where debit_id = :accountId or credit_id = :accountId order by date_at desc,time_at desc")
    fun pagingVoucherInAccount(accountId: Long): PagingSource<Int, ItemVoucher>

    @Query("select * from item_voucher where debit_id = :accountId or credit_id = :accountId order by date_at desc,time_at desc limit :limit")
    fun flowVoucherInAccountLimit(accountId: Long, limit: Int): Flow<List<ItemVoucher>>

    @Query("SELECT * FROM voucher WHERE row_id = :id")
    suspend fun findById(id: Long): Voucher
}