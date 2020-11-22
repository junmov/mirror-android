package cn.junmov.mirror.home.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import androidx.paging.insertSeparators
import androidx.paging.map
import cn.junmov.mirror.core.adapter.TwoLineModel
import cn.junmov.mirror.home.domain.PagingVoucherUseCase
import kotlinx.coroutines.flow.map

class HomeViewModel @ViewModelInject constructor(
    private val pagingVoucher: PagingVoucherUseCase
) : ViewModel() {

    val vouchers = pagingVoucher().map { value ->
        value.map { it.toTwoLineUiModel() }.insertSeparators { before, after ->
            when {
                before == null -> after?.let { TwoLineModel.Separator(it.separator) }
                after == null -> null
                before.separator > after.separator -> TwoLineModel.Separator(after.separator)
                else -> null
            }
        }
    }.cachedIn(viewModelScope)

}