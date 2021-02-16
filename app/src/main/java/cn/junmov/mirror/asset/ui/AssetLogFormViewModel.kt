package cn.junmov.mirror.asset.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import cn.junmov.mirror.asset.domain.ChangeAssetLogUseCase
import cn.junmov.mirror.asset.domain.FlowAssetLogUseCase
import cn.junmov.mirror.asset.domain.FlowAssetUseCase
import cn.junmov.mirror.core.data.db.entity.AssetLog
import cn.junmov.mirror.core.utility.MoneyUtils
import cn.junmov.mirror.core.utility.SnowFlakeUtil
import cn.junmov.mirror.core.utility.TimeUtils
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalDateTime

class AssetLogFormViewModel @ViewModelInject constructor(
    private val changeAssetLog: ChangeAssetLogUseCase,
    private val flowAssetLog: FlowAssetLogUseCase,
    private val flowAsset: FlowAssetUseCase
) : ViewModel() {

    val assetLog = MutableLiveData<AssetLog>()

    val inputAmount = MutableLiveData("0")
    val inputCount = MutableLiveData("0")
    val inputDateAt = MutableLiveData<String>()
    val shouldCreateVoucher = MutableLiveData(false)

    val updated = MutableLiveData<Boolean>()

    fun loadData(assetId: Long, assetLogId: Long) {
        if (assetLogId == 0L) {
            loadNewData(assetId)
        } else {
            loadOldData(assetLogId)
        }
    }

    private fun loadOldData(assetLogId: Long) {
        viewModelScope.launch {
            flowAssetLog(assetLogId).collectLatest {
                assetLog.value = it
                inputDateAt.value = TimeUtils.dateToString(it.dateAt)
                inputAmount.value = MoneyUtils.centToYuan(it.amount)
                inputCount.value = MoneyUtils.centToYuan(it.count)
            }
        }
    }

    private fun loadNewData(assetId: Long) {
        val now = LocalDateTime.now()
        viewModelScope.launch {
            flowAsset(assetId).collectLatest {
                assetLog.value = AssetLog(
                    id = SnowFlakeUtil.genId(), assetId = assetId, assetName = it.name,
                    buy = true, count = 0, amount = 0, dateAt = now.toLocalDate(), success = false,
                    createAt = now, modifiedAt = now, deleted = false,
                )
                inputDateAt.value = TimeUtils.dateToString(now.toLocalDate())
            }
        }
    }

    fun setDateAt(date: LocalDate) {
        inputDateAt.value = TimeUtils.dateToString(date)
    }

    fun submit() {
        val currentCount = inputCount.value ?: return
        val currentAmount = inputAmount.value ?: return
        val currentDateAt = inputDateAt.value ?: return
        val currentCreateVoucher = shouldCreateVoucher.value ?: return
        val currentAssetLog = assetLog.value ?: return
        currentAssetLog.amount = MoneyUtils.yuanToCent(currentAmount)
        currentAssetLog.count = MoneyUtils.yuanToCent(currentCount)
        currentAssetLog.dateAt = TimeUtils.stringToDate(currentDateAt)
        viewModelScope.launch {
            changeAssetLog(currentAssetLog, currentCreateVoucher)
            updated.value = true
        }
    }

}