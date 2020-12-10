package cn.junmov.mirror.budget.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cn.junmov.mirror.budget.domain.FlowAllSecondaryBudgetUseCase
import cn.junmov.mirror.core.data.entity.Account
import cn.junmov.mirror.core.utility.MoneyUtils
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class BudgetSecondaryViewModel @ViewModelInject constructor(
    private val flowSecondaryBudget: FlowAllSecondaryBudgetUseCase
) : ViewModel() {

    val secondaryBudgets = MutableLiveData<List<Account>>()

    val totalBudget = MutableLiveData<String>()

    fun loadData(budgetId: Long) {
        viewModelScope.launch {
            flowSecondaryBudget(budgetId).collectLatest { list ->
                secondaryBudgets.value = list
                totalBudget.value = MoneyUtils.centToYuan(list.sumOf { it.inflow + it.base })
            }
        }
    }

}