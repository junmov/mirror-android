package cn.junmov.mirror.asset.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import cn.junmov.mirror.asset.domain.ClinchOrderUseCase
import cn.junmov.mirror.asset.domain.FlowAllAssetLogUseCase
import cn.junmov.mirror.core.data.db.entity.AssetLog
import kotlinx.coroutines.launch

class AssetLogListViewModel @ViewModelInject constructor(
    private val flowAllAssetLog: FlowAllAssetLogUseCase,
    private val finishedOrder: ClinchOrderUseCase
) : ViewModel() {
    fun submitOrderAmount(id: Long, amount: String) {
        viewModelScope.launch {
            finishedOrder(id, amount)
        }
    }

    val assetLogs: LiveData<List<AssetLog>> = flowAllAssetLog().asLiveData()
}