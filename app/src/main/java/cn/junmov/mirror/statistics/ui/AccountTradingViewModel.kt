package cn.junmov.mirror.statistics.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.insertSeparators
import androidx.paging.map
import cn.junmov.mirror.core.adapter.TwoLineModel
import cn.junmov.mirror.statistics.domain.PagingAccountTradingUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class AccountTradingViewModel @ViewModelInject constructor(
    private val pagingAccountTrading: PagingAccountTradingUseCase
) : ViewModel() {

    fun loadData(accountId: Long): Flow<PagingData<TwoLineModel>> {
        return pagingAccountTrading(accountId).map { value ->
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
}