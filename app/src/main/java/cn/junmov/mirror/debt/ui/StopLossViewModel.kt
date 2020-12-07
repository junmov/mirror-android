package cn.junmov.mirror.debt.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import cn.junmov.mirror.core.data.entity.Debt
import cn.junmov.mirror.core.utility.MoneyUtils
import cn.junmov.mirror.debt.domain.FlowDebtUseCase
import cn.junmov.mirror.debt.domain.StopLossUseCase
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class StopLossViewModel @ViewModelInject constructor(
    private val flowDebt: FlowDebtUseCase,
    private val stopLoss: StopLossUseCase
) : ViewModel() {

    private val _debt = MutableLiveData<Debt>()

    val noSettledAmount = _debt.map { MoneyUtils.centToYuan(it.capital- it.capitalRepay) }

    val interest = MutableLiveData<String>()

    val updated = MutableLiveData<Boolean>()

    fun start(id: Long) {
        if (id == 0L) return
        viewModelScope.launch {
            flowDebt(id).collectLatest { _debt.value = it }
        }
    }

    fun stopLoss() {
        val currentAgingDebt = _debt.value ?: return
        val interestStr = interest.value ?: "0"
        val currentInterest = MoneyUtils.yuanToCent(interestStr)
        viewModelScope.launch {
            stopLoss(currentAgingDebt, currentInterest)
            updated.value = true
        }
    }

}