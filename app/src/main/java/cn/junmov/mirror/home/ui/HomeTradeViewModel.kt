package cn.junmov.mirror.home.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import cn.junmov.mirror.home.domain.FlowLastThreeVoucherUseCase
import cn.junmov.mirror.voucher.data.ItemVoucher

class HomeTradeViewModel @ViewModelInject constructor(
    private val flowLastThreeVoucher: FlowLastThreeVoucherUseCase
) : ViewModel() {

    val vouchers: LiveData<List<ItemVoucher>> = flowLastThreeVoucher().asLiveData()

}