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

class BudgetViewModel @ViewModelInject constructor(
    private val flowBudget: FlowAllBudgetByParentUseCase
)  : ViewModel() {
    val budgets = MutableLiveData<List<Budget>>()
    val total = MutableLiveData<String>()
    val used = MutableLiveData<String>()
    val useAble = MutableLiveData<String>()

    fun loadData() {
        viewModelScope.launch {
            var totalTmp = 0
            var usedTmp = 0
            flowBudget().collectLatest { list ->
                budgets.value = list
                list.forEach { budget ->
                    totalTmp += budget.total
                    usedTmp += budget.used
                }
                total.value = MoneyUtils.centToYuan(totalTmp)
                used.value = MoneyUtils.centToYuan(usedTmp)
                useAble.value = MoneyUtils.centToYuan(totalTmp - usedTmp)
            }
        }
    }
}