package cn.junmov.mirror.debt.domain

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import cn.junmov.mirror.core.data.dao.BillDao
import cn.junmov.mirror.core.data.entity.Bill
import kotlinx.coroutines.flow.Flow

class PagingBillBySettledUseCase(private val dao: BillDao) {
    operator fun invoke(settled: Boolean, size: Int = 15): Flow<PagingData<Bill>> {
        return Pager(PagingConfig(size)) { dao.pagingBillBySettled(settled) }.flow
    }
}
