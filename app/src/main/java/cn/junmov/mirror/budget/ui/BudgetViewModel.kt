package cn.junmov.mirror.budget.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cn.junmov.mirror.budget.domain.FlowAllFirstBudgetUseCase
import cn.junmov.mirror.core.data.entity.Account
import cn.junmov.mirror.core.utility.MoneyUtils
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class BudgetViewModel @ViewModelInject constructor(
    private val flowAllFirstBudget: FlowAllFirstBudgetUseCase
) : ViewModel() {
    val budgets = MutableLiveData<List<Account>>()
    val budgetTotal = MutableLiveData<String>()
    val budgetUsed = MutableLiveData<String>()
    val budgetUseAble = MutableLiveData<String>()
    val goalRevenue = MutableLiveData<String>()
    val goalReach = MutableLiveData<String>()
    val goalNotReach = MutableLiveData<String>()

    fun loadData() {
        viewModelScope.launch {
            var total = 0
            var used = 0
            var goal = 0
            var reach = 0
            flowAllFirstBudget().collectLatest { list ->
                budgets.value = list
                list.forEach { category ->
                    if (category.type.isIncome()) {
                        goal += category.base + category.inflow
                        reach += category.outflow
                    } else {
                        total += category.base + category.inflow
                        used += category.outflow
                    }
                }
                budgetTotal.value = MoneyUtils.centToYuan(total)
                budgetUsed.value = MoneyUtils.centToYuan(used)
                budgetUseAble.value = MoneyUtils.centToYuan(total - used)
                goalRevenue.value = MoneyUtils.centToYuan(goal)
                goalReach.value = MoneyUtils.centToYuan(reach)
                goalNotReach.value = MoneyUtils.centToYuan(goal - reach)
            }
        }
    }
}