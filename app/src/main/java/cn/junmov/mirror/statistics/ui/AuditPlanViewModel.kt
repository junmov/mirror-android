package cn.junmov.mirror.statistics.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.insertSeparators
import androidx.paging.map
import cn.junmov.mirror.core.adapter.TwoLineModel
import cn.junmov.mirror.statistics.domain.PagingScheduledVoucherUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class AuditPlanViewModel @ViewModelInject constructor(
    private val pagingScheduledVoucher: PagingScheduledVoucherUseCase
) : ViewModel() {

    val vouchers: Flow<PagingData<TwoLineModel>> = pagingScheduledVoucher().map { value ->
        value.map { it.twoLineData() }.insertSeparators { before, after ->
            when {
                before == null -> after?.let { TwoLineModel.Separator(it.separator) }
                after == null -> null
                before.separator > after.separator -> TwoLineModel.Separator(after.separator)
                else -> null
            }
        }
    }.cachedIn(viewModelScope)

}