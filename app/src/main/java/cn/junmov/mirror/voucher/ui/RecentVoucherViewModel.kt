package cn.junmov.mirror.voucher.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import cn.junmov.mirror.account.domain.BudgetMonthlyUseCase
import cn.junmov.mirror.core.data.db.view.ItemVoucher
import cn.junmov.mirror.core.utility.TimeUtils
import cn.junmov.mirror.voucher.domain.CopyVoucherUseCase
import cn.junmov.mirror.voucher.domain.FlowLastThreeVoucherUseCase
import cn.junmov.mirror.voucher.domain.RemoveVoucherUseCase
import kotlinx.coroutines.launch
import java.time.LocalDate

class RecentVoucherViewModel @ViewModelInject constructor(
    private val flowLastThreeVoucher: FlowLastThreeVoucherUseCase,
    private val copyVoucher: CopyVoucherUseCase,
    private val removeVoucher: RemoveVoucherUseCase,
) : ViewModel() {

    val vouchers: LiveData<List<ItemVoucher>> = flowLastThreeVoucher().asLiveData()

    fun copy(id: Long) {
        viewModelScope.launch { copyVoucher(id) }
    }

    fun remove(id: Long) {
        viewModelScope.launch { removeVoucher(id) }
    }

}