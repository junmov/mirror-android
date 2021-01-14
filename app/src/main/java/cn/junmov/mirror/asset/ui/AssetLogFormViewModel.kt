package cn.junmov.mirror.asset.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cn.junmov.mirror.asset.domain.CreateAssetLogUseCase
import cn.junmov.mirror.core.data.db.entity.AssetLog
import cn.junmov.mirror.core.utility.MoneyUtils
import cn.junmov.mirror.core.utility.SnowFlakeUtil
import kotlinx.coroutines.launch
import java.time.LocalDateTime

class AssetLogFormViewModel @ViewModelInject constructor(
    private val createAssetLog: CreateAssetLogUseCase
) : ViewModel() {

    val assetLog = MutableLiveData<AssetLog>()

    val inputAmount = MutableLiveData<String>()
    val inputCount = MutableLiveData<String>()

    val updated = MutableLiveData<Boolean>()

    fun loadData(assetId: Long) {
        val now = LocalDateTime.now()
        assetLog.value = AssetLog(
            id = SnowFlakeUtil.genId(), assetId = assetId, buy = true, count = 0,
            amount = 0, unitPrice = "",
            createAt = now, modifiedAt = now, deleted = false
        )
    }

    fun submit() {
        val currentCount = inputCount.value ?: return
        val currentAmount = inputAmount.value ?: return
        val currentAssetLog = assetLog.value ?: return
        currentAssetLog.amount = MoneyUtils.yuanToCent(currentAmount)
        currentAssetLog.count = MoneyUtils.yuanToCent(currentCount)
        viewModelScope.launch {
            createAssetLog(currentAssetLog)
            updated.value = true
        }
    }

}