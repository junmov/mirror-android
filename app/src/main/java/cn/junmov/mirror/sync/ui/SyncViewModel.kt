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

    val accountLastSync: LiveData<String> = repository.flowSyncAt(KEY_SYNC_AT_ACCOUNT).asLiveData()
    val balanceLastSync: LiveData<String> = repository.flowSyncAt(KEY_SYNC_AT_BALANCE).asLiveData()
    val thingLastSync: LiveData<String> = repository.flowSyncAt(KEY_SYNC_AT_THING).asLiveData()
    val voucherLastSync: LiveData<String> = repository.flowSyncAt(KEY_SYNC_AT_VOUCHER).asLiveData()
    val splitLastSync: LiveData<String> = repository.flowSyncAt(KEY_SYNC_AT_SPLIT).asLiveData()
    val tradeLastSync: LiveData<String> = repository.flowSyncAt(KEY_SYNC_AT_TRADE).asLiveData()
    val assetLastSync: LiveData<String> = repository.flowSyncAt(KEY_SYNC_AT_ASSET).asLiveData()
    val assetLogLastSync: LiveData<String> =
        repository.flowSyncAt(KEY_SYNC_AT_ASSET_LOG).asLiveData()
    val billLastSync: LiveData<String> = repository.flowSyncAt(KEY_SYNC_AT_BILL).asLiveData()
    val debtLastSync: LiveData<String> = repository.flowSyncAt(KEY_SYNC_AT_DEBT).asLiveData()
    val repayLastSync: LiveData<String> = repository.flowSyncAt(KEY_SYNC_AT_REPAY).asLiveData()
    val todoLastSync: LiveData<String> = repository.flowSyncAt(KEY_SYNC_AT_TODO).asLiveData()

    val message = MutableLiveData<String>()

    init {
        inputIpAddress.value = "192.168.43.147:8080"
    }

    fun pushAccount() {
        val currentIp = inputIpAddress.value ?: return
        viewModelScope.launch {
            message.value = repository.pushAccount(currentIp)
        }
    }

    fun pullAccount() {
        val currentIp = inputIpAddress.value ?: return
        viewModelScope.launch {
            message.value = repository.pullAccount(currentIp)
        }
    }

    fun pushBalance() {
        val currentIp = inputIpAddress.value ?: return
        viewModelScope.launch {
            message.value = repository.pushBalance(currentIp)
        }
    }

    fun pullBalance() {
        val currentIp = inputIpAddress.value ?: return
        viewModelScope.launch {
            message.value = repository.pullBalance(currentIp)
        }
    }

    fun pushThing() {
        val currentIp = inputIpAddress.value ?: return
        viewModelScope.launch {
            message.value = repository.pushThing(currentIp)
        }
    }

    fun pullThing() {
        val currentIp = inputIpAddress.value ?: return
        viewModelScope.launch {
            message.value = repository.pullThing(currentIp)
        }
    }

    fun pushVoucher() {
        val currentIp = inputIpAddress.value ?: return
        viewModelScope.launch {
            message.value = repository.pushVoucher(currentIp)
        }
    }

    fun pullVoucher() {
        val currentIp = inputIpAddress.value ?: return
        viewModelScope.launch {
            message.value = repository.pullVoucher(currentIp)
        }
    }

    fun pushSplit() {
        val currentIp = inputIpAddress.value ?: return
        viewModelScope.launch {
            message.value = repository.pushSplit(currentIp)
        }
    }

    fun pullSplit() {
        val currentIp = inputIpAddress.value ?: return
        viewModelScope.launch {
            message.value = repository.pullSplit(currentIp)
        }
    }

    fun pushTrade() {
        val currentIp = inputIpAddress.value ?: return
        viewModelScope.launch {
            message.value = repository.pushTrade(currentIp)
        }
    }

    fun pullTrade() {
        val currentIp = inputIpAddress.value ?: return
        viewModelScope.launch {
            message.value = repository.pullTrade(currentIp)
        }
    }

    fun pushAsset() {
        val currentIp = inputIpAddress.value ?: return
        viewModelScope.launch {
            message.value = repository.pushAsset(currentIp)
        }
    }

    fun pullAsset() {
        val currentIp = inputIpAddress.value ?: return
        viewModelScope.launch {
            message.value = repository.pullAsset(currentIp)
        }
    }

    fun pushAssetLog() {
        val currentIp = inputIpAddress.value ?: return
        viewModelScope.launch {
            message.value = repository.pushAssetLog(currentIp)
        }
    }

    fun pullAssetLog() {
        val currentIp = inputIpAddress.value ?: return
        viewModelScope.launch {
            message.value = repository.pullAssetLog(currentIp)
        }
    }

    fun pushBill() {
        val currentIp = inputIpAddress.value ?: return
        viewModelScope.launch {
            message.value = repository.pushBill(currentIp)
        }
    }

    fun pullBill() {
        val currentIp = inputIpAddress.value ?: return
        viewModelScope.launch {
            message.value = repository.pullBill(currentIp)
        }
    }

    fun pushDebt() {
        val currentIp = inputIpAddress.value ?: return
        viewModelScope.launch {
            message.value = repository.pushDebt(currentIp)
        }
    }

    fun pullDebt() {
        val currentIp = inputIpAddress.value ?: return
        viewModelScope.launch {
            message.value = repository.pullDebt(currentIp)
        }
    }

    fun pushRepay() {
        val currentIp = inputIpAddress.value ?: return
        viewModelScope.launch {
            message.value = repository.pushRepay(currentIp)
        }
    }

    fun pullRepay() {
        val currentIp = inputIpAddress.value ?: return
        viewModelScope.launch {
            message.value = repository.pullRepay(currentIp)
        }
    }

    fun pushTodo() {
        val currentIp = inputIpAddress.value ?: return
        viewModelScope.launch {
            message.value = repository.pushTodo(currentIp)
        }
    }

    fun pullTodo() {
        val currentIp = inputIpAddress.value ?: return
        viewModelScope.launch {
            message.value = repository.pullTodo(currentIp)
        }
    }
}