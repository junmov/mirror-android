package cn.junmov.mirror.voucher.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import cn.junmov.mirror.R
import cn.junmov.mirror.core.data.entity.Split
import cn.junmov.mirror.core.data.model.VoucherAndSplits
import cn.junmov.mirror.core.utility.TimeUtils
import cn.junmov.mirror.voucher.domain.AuditVoucherUseCase
import cn.junmov.mirror.voucher.domain.CopyVoucherUseCase
import cn.junmov.mirror.voucher.domain.FlowVoucherAndSplitsUseCase
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.time.LocalDateTime

class VoucherDetailViewModel @ViewModelInject constructor(
    private val flowVoucherInfo: FlowVoucherAndSplitsUseCase,
    private val auditVoucher: AuditVoucherUseCase,
    private val copyVoucher: CopyVoucherUseCase,
) : ViewModel() {

    private val _voucherInfo = MutableLiveData<VoucherAndSplits>()

    val voucher: LiveData<cn.junmov.mirror.core.data.entity.Voucher> = _voucherInfo.map { it.voucher }

    val occurAt: LiveData<String> = voucher.map {
        TimeUtils.dateTimeToString(LocalDateTime.of(it.dateAt, it.timeAt))
    }

    val splits: LiveData<List<Split>> = _voucherInfo.map { it.splits }

    val message = MutableLiveData<Int>()
    val updated = MutableLiveData<Boolean>()

    fun loadData(voucherId: Long) {
        if (voucherId == 0L) return
        viewModelScope.launch {
            flowVoucherInfo(voucherId).collectLatest { _voucherInfo.value = it }
        }
    }

    fun copy() {
        val currentVoucher = _voucherInfo.value ?: return
        viewModelScope.launch {
            copyVoucher(currentVoucher)
            message.value = R.string.success_copy_voucher
        }
    }

    fun audit() {
        val currentVoucherInfo = _voucherInfo.value ?: return
        if (!currentVoucherInfo.auditAble()) {
            message.value = R.string.error_audit_voucher
            return
        }
        viewModelScope.launch {
            auditVoucher(currentVoucherInfo)
        }
    }

}