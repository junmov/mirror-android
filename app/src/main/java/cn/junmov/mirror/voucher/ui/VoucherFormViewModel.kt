package cn.junmov.mirror.voucher.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import cn.junmov.mirror.core.data.db.entity.Thing
import cn.junmov.mirror.core.data.db.entity.Voucher
import cn.junmov.mirror.core.data.model.VoucherType
import cn.junmov.mirror.core.utility.SnowFlakeUtil
import cn.junmov.mirror.core.utility.TimeUtils
import cn.junmov.mirror.thing.domain.FlowAllThingUseCase
import cn.junmov.mirror.voucher.domain.FlowVoucherUseCase
import cn.junmov.mirror.voucher.domain.SaveVoucherUseCase
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.time.LocalDateTime

class VoucherFormViewModel @ViewModelInject constructor(
    private val flowAllThing: FlowAllThingUseCase,
    private val flowVoucher: FlowVoucherUseCase,
    private val saveVoucher: SaveVoucherUseCase
) : ViewModel() {

    val things: LiveData<List<Thing>> = flowAllThing().asLiveData()

    val voucher = MutableLiveData<Voucher>()

    val inputDateAt = MutableLiveData<String>()
    val inputTimeAt = MutableLiveData<String>()

    private var _isCreate = false
    val updated = MutableLiveData<Boolean>()

    fun loadData(voucherId: Long) {
        if (voucherId == 0L) {
            loadNewData()
        } else {
            loadOldData(voucherId)
        }
    }

    private fun loadOldData(voucherId: Long) {
        viewModelScope.launch {
            flowVoucher(voucherId).collectLatest {
                voucher.value = it
                inputDateAt.value = TimeUtils.dateToString(it.dateAt)
                inputTimeAt.value = TimeUtils.timeToString(it.timeAt)
            }
        }
    }

    private fun loadNewData() {
        val now = LocalDateTime.now()
        voucher.value = Voucher(
            id = SnowFlakeUtil.genId(), summary = "", dateAt = now.toLocalDate(),
            timeAt = now.toLocalTime(), type = VoucherType.TRANSFER,
            thingId = 0, thingName = "", profit = 0, template = false, audited = false,
            createAt = now, modifiedAt = now
        )
        inputDateAt.value = TimeUtils.dateToString(now.toLocalDate())
        inputTimeAt.value = TimeUtils.timeToString(now.toLocalTime())
        _isCreate = true
    }

    fun selectThing(thing: Thing) {
        voucher.value?.thingId = thing.id
        voucher.value?.thingName = thing.name
    }

    fun submitVoucher() {
        val currentVoucher = voucher.value ?: return
        val dateAtStr = inputDateAt.value ?: return
        val timeAtStr = inputTimeAt.value ?: return
        currentVoucher.dateAt = TimeUtils.stringToDate(dateAtStr)
        currentVoucher.timeAt = TimeUtils.stringToTime(timeAtStr)
        viewModelScope.launch {
            saveVoucher(currentVoucher, _isCreate)
            updated.value = true
        }
    }

}