package cn.junmov.mirror.asset.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import cn.junmov.mirror.asset.domain.FlowAllAssetLogUseCase
import cn.junmov.mirror.asset.domain.FlowAssetUseCase
import cn.junmov.mirror.core.data.db.entity.Asset
import cn.junmov.mirror.core.data.db.entity.AssetLog

class AssetDetailViewModel @ViewModelInject constructor(
    private val flowAsset: FlowAssetUseCase,
    private val flowAllAssetLog: FlowAllAssetLogUseCase
) : ViewModel() {

    private val _assetId = MutableLiveData<Long>()

    val asset: LiveData<Asset> = _assetId.switchMap { flowAsset(it).asLiveData() }

    val assetLogs: LiveData<List<AssetLog>> =
        _assetId.switchMap { flowAllAssetLog(it).asLiveData() }

    fun loadData(assetId: Long) {
        _assetId.value = assetId
    }
}