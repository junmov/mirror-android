package cn.junmov.mirror.asset.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import cn.junmov.mirror.asset.domain.CreateAssetUseCase
import cn.junmov.mirror.asset.domain.FlowAllAssetUseCase
import cn.junmov.mirror.core.data.db.entity.Asset
import kotlinx.coroutines.launch

class AssetViewModel @ViewModelInject constructor(
    private val flowAllAsset: FlowAllAssetUseCase,
    private val createAsset: CreateAssetUseCase,
) : ViewModel() {

    private val showOwnerAssets = MutableLiveData(false)

    private val allAssets = flowAllAsset().asLiveData()

    val assets: LiveData<List<Asset>> = showOwnerAssets.switchMap { show ->
        if (show) {
            allAssets.map { list -> list.filter { it.count != 0 } }
        } else {
            allAssets
        }
    }

    fun toggleFilter() {
        val filter = showOwnerAssets.value ?: false
        showOwnerAssets.value = !filter
    }

    fun submitAsset(name: String) {
        viewModelScope.launch {
            createAsset(name)
        }
    }
}