package cn.junmov.mirror.mine.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import cn.junmov.mirror.mine.data.MineRepository
import cn.junmov.mirror.mine.data.local.*
import kotlinx.coroutines.launch

class MineViewModel @ViewModelInject constructor(
    private val repository: MineRepository
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

    fun pushBalance() {
        val currentIp = inputIpAddress.value ?: return
        viewModelScope.launch {
            message.value = repository.pushBalance(currentIp)
        }
    }

    fun pushThing() {
        val currentIp = inputIpAddress.value ?: return
        viewModelScope.launch {
            message.value = repository.pushThing(currentIp)
        }
    }

    fun pushVoucher() {
        val currentIp = inputIpAddress.value ?: return
        viewModelScope.launch {
            message.value = repository.pushVoucher(currentIp)
        }
    }

    fun pushSplit() {
        val currentIp = inputIpAddress.value ?: return
        viewModelScope.launch {
            message.value = repository.pushSplit(currentIp)
        }
    }

    fun pushTrade() {
        val currentIp = inputIpAddress.value ?: return
        viewModelScope.launch {
            message.value = repository.pushTrade(currentIp)
        }
    }

    fun pushAsset() {
        val currentIp = inputIpAddress.value ?: return
        viewModelScope.launch {
            message.value = repository.pushAsset(currentIp)
        }
    }

    fun pushAssetLog() {
        val currentIp = inputIpAddress.value ?: return
        viewModelScope.launch {
            message.value = repository.pushAssetLog(currentIp)
        }
    }

    fun pushBill() {
        val currentIp = inputIpAddress.value ?: return
        viewModelScope.launch {
            message.value = repository.pushBill(currentIp)
        }
    }

    fun pushDebt() {
        val currentIp = inputIpAddress.value ?: return
        viewModelScope.launch {
            message.value = repository.pushDebt(currentIp)
        }
    }

    fun pushRepay() {
        val currentIp = inputIpAddress.value ?: return
        viewModelScope.launch {
            message.value = repository.pushRepay(currentIp)
        }
    }

    fun pushTodo() {
        val currentIp = inputIpAddress.value ?: return
        viewModelScope.launch {
            message.value = repository.pushTodo(currentIp)
        }
    }
}