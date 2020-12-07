package cn.junmov.mirror.budget.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cn.junmov.mirror.budget.domain.FlowAllFirstBudgetUseCase
import cn.junmov.mirror.core.data.model.Category
import cn.junmov.mirror.core.utility.MoneyUtils
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class BudgetViewModel @ViewModelInject constructor(
    private val flowAllFirstBudget: FlowAllFirstBudgetUseCase
)  : ViewModel() {
    val budgets = MutableLiveData<List<Category>>()
    val total = MutableLiveData<String>()
    val used = MutableLiveData<String>()
    val useAble = MutableLiveData<String>()

    fun loadData() {
        viewModelScope.launch {
            var totalTmp = 0
            var usedTmp = 0
            flowAllFirstBudget().collectLatest { list ->
                budgets.value = list
                list.forEach { category ->
                    totalTmp += category.budgetTotal
                    usedTmp += category.budgetUsed
                }
                total.value = MoneyUtils.centToYuan(totalTmp)
                used.value = MoneyUtils.centToYuan(usedTmp)
                useAble.value = MoneyUtils.centToYuan(totalTmp - usedTmp)
            }
        }
    }
}