package cn.junmov.mirror.thing.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.insertSeparators
import androidx.paging.map
import cn.junmov.mirror.R
import cn.junmov.mirror.core.adapter.TwoLineModel
import cn.junmov.mirror.thing.domain.PagingVoucherByThingUseCase
import cn.junmov.mirror.thing.domain.RenameThingUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class ThingDetailViewModel @ViewModelInject constructor(
    private val renameThing: RenameThingUseCase,
    private val pagingVoucherByThing: PagingVoucherByThingUseCase
) : ViewModel() {

    private var thingVouchers: Flow<PagingData<TwoLineModel>>? = null

    val message = MutableLiveData<Int>()

    fun fetchData(id: Long): Flow<PagingData<TwoLineModel>> {
        val newData = pagingVoucherByThing(id).map { data ->
            data.map { it.toTwoLineUiModel() }
                .insertSeparators { before, after ->
                    when {
                        before == null -> after?.let { TwoLineModel.Separator(it.separator) }
                        after == null -> null
                        before.separator > after.separator -> TwoLineModel.Separator(after.separator)
                        else -> null
                    }
                }
        }.cachedIn(viewModelScope)
        thingVouchers = newData
        return newData
    }

    fun rename(newName: String, eventId: Long, oldName: String) {
        if (newName == oldName) {
            message.value = R.string.error_thing_name_no_change
            return
        }
        message.value = 0
        viewModelScope.launch {
            renameThing(eventId, newName)
        }
    }

}