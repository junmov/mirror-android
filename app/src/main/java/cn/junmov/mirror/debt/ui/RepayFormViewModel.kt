package cn.junmov.mirror.debt.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cn.junmov.mirror.core.data.db.entity.Repay
import cn.junmov.mirror.core.utility.MoneyUtils
import cn.junmov.mirror.debt.domain.FlowRepayUseCase
import cn.junmov.mirror.debt.domain.UpdateRepayUseCase
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class RepayFormViewModel @ViewModelInject constructor(
    private val flowRepay: FlowRepayUseCase,
    private val updateRepay: UpdateRepayUseCase
) : ViewModel() {

    private val repay = MutableLiveData<Repay>()

    val inputAmount = MutableLiveData<String>()
    val inputInterest = MutableLiveData<String>()

    val updated = MutableLiveData<Boolean>()

    fun loadData(itemId: Long) {
        viewModelScope.launch {
            flowRepay(itemId).collectLatest {
                repay.value = it
                inputAmount.value = MoneyUtils.centToYuan(it.capital)
                inputInterest.value = MoneyUtils.centToYuan(it.interest)
            }
        }
    }

    fun submit() {
        val amountStr = inputAmount.value ?: return
        val interestStr = inputInterest.value ?: return
        val currentRepay = repay.value ?: return
        val amount = MoneyUtils.yuanToCent(amountStr)
        val interest = MoneyUtils.yuanToCent(interestStr)
        viewModelScope.launch {
            updateRepay(currentRepay, amount, interest)
            updated.value = true
        }
    }
}