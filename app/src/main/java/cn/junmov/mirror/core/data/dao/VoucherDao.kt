package cn.junmov.mirror.core.data.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import cn.junmov.mirror.core.data.entity.Voucher
import cn.junmov.mirror.voucher.data.VoucherInfo
import kotlinx.coroutines.flow.Flow

@Dao
interface VoucherDao : BaseDao<Voucher> {

    @Transaction
    @Query("SELECT * FROM voucher WHERE row_id = :id")
    fun flowVoucherInfo(id: Long): Flow<VoucherInfo>

}