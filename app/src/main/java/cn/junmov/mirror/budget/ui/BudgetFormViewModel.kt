package cn.junmov.mirror.budget.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cn.junmov.mirror.budget.domain.FlowBudgetUseCase
import cn.junmov.mirror.budget.domain.UpdateBudgetUseCase
import cn.junmov.mirror.core.data.entity.Budget
import cn.junmov.mirror.core.utility.MoneyUtils
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class BudgetFormViewModel @ViewModelInject constructor(
    private val flowBudget: FlowBudgetUseCase,
    private val updateBudget: UpdateBudgetUseCase
) : ViewModel() {

    val budget = MutableLiveData<Budget>()

    val inputAmount = MutableLiveData<String>()

    val updated = MutableLiveData<Boolean>()

    fun loadData(budgetId: Long) {
        viewModelScope.launch {
            flowBudget(budgetId).collectLatest {
                budget.value = it
            }
        }
    }

    fun submit() {
        val currentBudget = budget.value ?: return
        val currentAmountStr = inputAmount.value ?: return
        if (!MoneyUtils.isFormat(currentAmountStr)) {
            return
        }
        val currentAmount = MoneyUtils.yuanToCent(currentAmountStr)
        viewModelScope.launch {
            updateBudget(currentBudget, currentAmount)
            updated.value = true
        }
    }
}