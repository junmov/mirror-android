package cn.junmov.mirror.asset.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import cn.junmov.mirror.asset.domain.FlowAllAssetLogUseCase
import cn.junmov.mirror.asset.domain.FlowAssetUseCase
import cn.junmov.mirror.core.data.db.entity.Asset
import cn.junmov.mirror.core.data.db.entity.AssetLog
import cn.junmov.mirror.core.utility.MoneyUtils

class AssetDetailViewModel @ViewModelInject constructor(
    private val flowAsset: FlowAssetUseCase,
    private val flowAllAssetLog: FlowAllAssetLogUseCase
) : ViewModel() {

    private val _assetId = MutableLiveData<Long>()

    val asset: LiveData<Asset> = _assetId.switchMap { flowAsset(it).asLiveData() }

    val assetLogs: LiveData<List<AssetLog>> =
        _assetId.switchMap { flowAllAssetLog(it).asLiveData() }

    val count: LiveData<String> = asset.map { "持有数量： ${MoneyUtils.centToYuan(it.count)}" }

    val cost: LiveData<String> = asset.map { "总投入成本： ${MoneyUtils.centToYuan(it.buy)}" }

    val earning: LiveData<String> = asset.map { "已获得回报： ${MoneyUtils.centToYuan(it.sell)}" }

    fun loadData(assetId: Long) {
        _assetId.value = assetId
    }
}