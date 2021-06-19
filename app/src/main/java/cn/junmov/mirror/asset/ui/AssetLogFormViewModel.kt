package cn.junmov.mirror.asset.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import cn.junmov.mirror.asset.domain.EntrustOrderUseCase
import cn.junmov.mirror.asset.domain.FlowHoldAssetsUseCase
import cn.junmov.mirror.core.data.db.entity.Asset
import cn.junmov.mirror.core.utility.TimeUtils
import kotlinx.coroutines.launch
import java.time.LocalDate

class AssetLogFormViewModel @ViewModelInject constructor(
    private val changeAssetLog: EntrustOrderUseCase,
    private val flowAssets: FlowHoldAssetsUseCase
) : ViewModel() {

    val assets: LiveData<List<Asset>> = flowAssets().asLiveData()

    private val inputAssetId = MutableLiveData<Long>()
    val inputAssetName = MutableLiveData<String>()

    val inputBuy = MutableLiveData(true)
    val inputCount = MutableLiveData<String>()
    val inputPrice = MutableLiveData<String>()
    val inputDateAt = MutableLiveData(TimeUtils.dateToString(LocalDate.now()))
    val inputReason = MutableLiveData("")

    val updated = MutableLiveData<Boolean>()

    fun selectAsset(a: Asset) {
        inputAssetId.value = a.id
        inputAssetName.value = a.name
    }

    fun setDateAt(date: LocalDate) {
        inputDateAt.value = TimeUtils.dateToString(date)
    }

    fun submit() {
        val currentCount = inputCount.value ?: return
        val currentPrice = inputPrice.value ?: return
        val currentDateAt = inputDateAt.value ?: return
        val currentBuy = inputBuy.value ?: return
        val currentReason = inputReason.value ?: return
        val currentAssetName = inputAssetName.value ?: return
        val currentAssetId = inputAssetId.value ?: 0L

        viewModelScope.launch {
            changeAssetLog(
                currentAssetId,
                currentAssetName,
                currentBuy,
                currentCount.toInt(),
                currentPrice,
                TimeUtils.stringToDate(currentDateAt),
                currentReason
            )
            updated.value = true
        }
    }

}