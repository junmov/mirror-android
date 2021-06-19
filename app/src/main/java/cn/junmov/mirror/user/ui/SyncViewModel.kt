package cn.junmov.mirror.user.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import cn.junmov.mirror.core.data.store.*
import cn.junmov.mirror.user.domain.SyncRepository
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class SyncViewModel @ViewModelInject constructor(
    private val repository: SyncRepository
) : ViewModel() {

    val inputIpAddress = MutableLiveData<String>()

    val lastSync: LiveData<String> =
        repository.flowSyncAt(KEY_SYNC_AT).map { "上次同步：${it}" }.asLiveData()

    val message = MutableLiveData<String>()

    init {
        inputIpAddress.value = "192.168.43.147:8080"
    }

    fun sync(){
        val currentIp = inputIpAddress.value ?: return
        viewModelScope.launch {
            message.value = repository.sync(currentIp)
        }
    }

}