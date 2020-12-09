package cn.junmov.mirror.thing.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import cn.junmov.mirror.thing.domain.CreateThingUseCase
import cn.junmov.mirror.thing.domain.FlowAllThingUseCase
import kotlinx.coroutines.launch

class ThingViewModel @ViewModelInject constructor(
    private val flowAll: FlowAllThingUseCase,
    private val createThing: CreateThingUseCase
) : ViewModel() {

    val things = flowAll().asLiveData()

    fun submitName(name: String?) {
        if (name.isNullOrBlank()) return
        viewModelScope.launch {
            createThing(name)
        }
    }
}
