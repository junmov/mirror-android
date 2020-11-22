package cn.junmov.mirror.budget.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cn.junmov.mirror.budget.domain.FlowAllBudgetByParentUseCase
import cn.junmov.mirror.core.data.entity.Budget
import cn.junmov.mirror.core.utility.MoneyUtils
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class BudgetSecondaryViewModel @ViewModelInject constructor(
    private val flowBudget: FlowAllBudgetByParentUseCase
) : ViewModel() {

    val secondaryBudgets = MutableLiveData<List<Budget>>()

    val totalBudget = MutableLiveData<String>()

    fun loadData(budgetId: Long) {
        viewModelScope.launch {
            flowBudget(budgetId).collectLatest { list ->
                secondaryBudgets.value = list
                totalBudget.value = MoneyUtils.centToYuan(list.sumOf { it.total })
            }
        }
    }

}