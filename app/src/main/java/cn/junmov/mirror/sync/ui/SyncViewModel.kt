package cn.junmov.mirror.sync.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import cn.junmov.mirror.core.data.store.*
import cn.junmov.mirror.sync.data.SyncRepository
import kotlinx.coroutines.launch

class SyncViewModel @ViewModelInject constructor(
    private val repository: SyncRepository
) : ViewModel() {

    val inputIpAddress = MutableLiveData<String>()

    val lastSync: LiveData<String> = repository.flowSyncAt(KEY_SYNC_AT).asLiveData()

    val message = MutableLiveData<String>()

    init {
        inputIpAddress.value = "192.168.43.147:8080"
    }

    fun pull() {
        val currentIp = inputIpAddress.value ?: return
        viewModelScope.launch {
            message.value = repository.pull(currentIp)
        }
    }

    fun push() {
        val currentIp = inputIpAddress.value ?: return
        viewModelScope.launch {
            message.value = repository.push(currentIp)
        }
    }
}