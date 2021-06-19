package cn.junmov.mirror.asset.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import cn.junmov.mirror.asset.domain.FlowAllAssetUseCase
import cn.junmov.mirror.core.data.db.entity.Asset

class AssetViewModel @ViewModelInject constructor(
    private val flowAllAsset: FlowAllAssetUseCase,
) : ViewModel() {

    private val showOwnerAssets = MutableLiveData(true)

    private val allAssets = flowAllAsset().asLiveData()

    val assets: LiveData<List<Asset>> = showOwnerAssets.switchMap { show ->
        if (show) {
            allAssets.map { list -> list.filter { it.active } }
        } else {
            allAssets
        }
    }

    fun toggleFilter() {
        val filter = showOwnerAssets.value ?: true
        showOwnerAssets.value = !filter
    }

}