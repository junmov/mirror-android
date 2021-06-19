package cn.junmov.mirror.asset.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import cn.junmov.mirror.asset.domain.FlowAllLogOfAssetUseCase
import cn.junmov.mirror.core.data.db.entity.AssetLog

class AssetDetailViewModel @ViewModelInject constructor(
    private val flowAllAssetLog: FlowAllLogOfAssetUseCase
) : ViewModel() {

    private val _assetId = MutableLiveData<Long>()

    val assetLogs: LiveData<List<AssetLog>> =
        _assetId.switchMap { flowAllAssetLog(it).asLiveData() }

    fun loadData(assetId: Long) {
        _assetId.value = assetId
    }
}