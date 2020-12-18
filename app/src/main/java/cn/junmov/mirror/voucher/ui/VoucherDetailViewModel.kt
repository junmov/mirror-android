package cn.junmov.mirror.voucher.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import cn.junmov.mirror.R
import cn.junmov.mirror.core.data.entity.Split
import cn.junmov.mirror.core.data.entity.Voucher
import cn.junmov.mirror.core.utility.TimeUtils
import cn.junmov.mirror.voucher.domain.*
import kotlinx.coroutines.launch
import java.time.LocalDateTime

class VoucherDetailViewModel @ViewModelInject constructor(
    private val auditVoucher: AuditVoucherUseCase,
    private val flowSplits: FlowAllSplitByVoucherUseCase,
    private val flowVoucher: FlowVoucherUseCase,
    private val copyVoucher: CopyVoucherUseCase,
) : ViewModel() {

    private val _voucherId = MutableLiveData<Long>()

    val voucher: LiveData<Voucher> = _voucherId.switchMap { flowVoucher(it).asLiveData() }

    val occurAt: LiveData<String> = voucher.map {
        TimeUtils.dateTimeToString(LocalDateTime.of(it.dateAt, it.timeAt))
    }

    val splits: LiveData<List<Split>> = _voucherId.switchMap { flowSplits(it).asLiveData() }

    val message = MutableLiveData<Int>()
    val updated = MutableLiveData<Boolean>()

    fun loadData(voucherId: Long) {
        _voucherId.value = voucherId
    }

    fun copy() {
        val currentVoucher = voucher.value ?: return
        val currentSplits = splits.value ?: return
        viewModelScope.launch {
            copyVoucher(currentVoucher, currentSplits)
            message.value = R.string.success_copy_voucher
        }
    }

    fun audit() {
        val currentVoucher = voucher.value ?: return
        val currentSplits = splits.value ?: return
        var debit = 0
        var credit = 0
        currentSplits.forEach {
            if (it.debit) {
                debit += it.amount
            } else {
                credit += it.amount
            }
        }
        if (debit == 0 || debit != credit) {
            message.value = R.string.error_audit_voucher
            return
        }
        viewModelScope.launch {
            auditVoucher(currentVoucher, currentSplits)
        }
    }

}