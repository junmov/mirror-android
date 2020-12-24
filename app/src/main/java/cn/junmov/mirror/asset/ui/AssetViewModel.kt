package cn.junmov.mirror.asset.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import cn.junmov.mirror.asset.domain.CreateAssetUseCase
import cn.junmov.mirror.asset.domain.FlowAllAssetUseCase
import cn.junmov.mirror.core.data.db.entity.Asset
import kotlinx.coroutines.launch

class AssetViewModel @ViewModelInject constructor(
    private val flowAllAsset: FlowAllAssetUseCase,
    private val createAsset: CreateAssetUseCase
) : ViewModel() {

    val assets: LiveData<List<Asset>> = flowAllAsset().asLiveData()

    fun submitAsset(name: String) {
        viewModelScope.launch {
            createAsset(name)
        }
    }
}