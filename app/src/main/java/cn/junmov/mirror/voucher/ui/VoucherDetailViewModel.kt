package cn.junmov.mirror.voucher.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import cn.junmov.mirror.R
import cn.junmov.mirror.core.data.entity.Split
import cn.junmov.mirror.core.data.entity.Voucher
import cn.junmov.mirror.core.utility.TimeUtils
import cn.junmov.mirror.voucher.data.VoucherInfo
import cn.junmov.mirror.voucher.domain.CopyVoucherUseCase
import cn.junmov.mirror.voucher.domain.FlowVoucherInfoUseCase
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.time.LocalDateTime

class VoucherDetailViewModel @ViewModelInject constructor(
    private val flowVoucherInfo: FlowVoucherInfoUseCase,
    private val copyVoucher: CopyVoucherUseCase,
) : ViewModel() {

    private val _voucherInfo = MutableLiveData<VoucherInfo>()

    private val voucherInfo: LiveData<VoucherInfo> = _voucherInfo

    val voucher: LiveData<Voucher> = voucherInfo.map { it.voucher }

    val occurAt: LiveData<String> = voucher.map {
        TimeUtils.dateTimeToString(LocalDateTime.of(it.dateAt, it.timeAt))
    }

    val splits: LiveData<List<Split>> = voucherInfo.map { it.splits }

    val message = MutableLiveData<Int>()
    val updated = MutableLiveData<Boolean>()

    fun start(id: Long) {
        if (id == 0L) return
        viewModelScope.launch {
            flowVoucherInfo(id).collectLatest { _voucherInfo.value = it }
        }
    }

    fun copy() {
        val currentVoucher = voucherInfo.value ?: return
        viewModelScope.launch {
            copyVoucher(currentVoucher)
            message.value = R.string.success_copy_voucher
        }
    }

}