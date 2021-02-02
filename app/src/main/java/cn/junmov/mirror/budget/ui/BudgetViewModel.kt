package cn.junmov.mirror.budget.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cn.junmov.mirror.budget.data.Budget
import cn.junmov.mirror.budget.domain.FlowAllFirstBudgetUseCase
import cn.junmov.mirror.core.utility.MoneyUtils
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class BudgetViewModel @ViewModelInject constructor(
    private val flowAllFirstBudget: FlowAllFirstBudgetUseCase
) : ViewModel() {
    val budgets = MutableLiveData<List<Budget>>()
    val budgetTotal = MutableLiveData<String>()
    val budgetUsed = MutableLiveData<String>()
    val goalRevenue = MutableLiveData<String>()
    val goalReach = MutableLiveData<String>()

    fun loadData() {
        viewModelScope.launch {
            var total = 0
            var used = 0
            var goal = 0
            var reach = 0
            flowAllFirstBudget().collectLatest { list ->
                budgets.value = list
                list.forEach { budget ->
                    if (budget.type.isIncome()) {
                        goal += budget.total()
                        reach += budget.used()
                    } else {
                        total += budget.total()
                        used += budget.used()
                    }
                }
                budgetTotal.value = MoneyUtils.centToYuan(total)
                budgetUsed.value = MoneyUtils.centToYuan(used)
                goalRevenue.value = MoneyUtils.centToYuan(goal)
                goalReach.value = MoneyUtils.centToYuan(reach)
            }
        }
    }
}