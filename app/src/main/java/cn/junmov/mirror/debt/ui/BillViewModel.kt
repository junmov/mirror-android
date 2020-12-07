package cn.junmov.mirror.debt.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.insertSeparators
import androidx.paging.map
import cn.junmov.mirror.core.adapter.SingleLineModel
import cn.junmov.mirror.debt.domain.PagingBillBySettledUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class BillViewModel @ViewModelInject constructor(
    private val pagingBill: PagingBillBySettledUseCase
) : ViewModel() {

    val bills: Flow<PagingData<SingleLineModel>> = pagingBill(false).map { value ->
        value.map { it.singleLineData() }.insertSeparators { before, after ->
            when {
                before == null -> after?.let { SingleLineModel.Separator(it.separator) }
                after == null -> null
                before.separator < after.separator -> SingleLineModel.Separator(after.separator)
                else -> null
            }
        }
    }.cachedIn(viewModelScope)
}
