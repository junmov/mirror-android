package cn.junmov.mirror.budget.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cn.junmov.mirror.budget.domain.FlowBudgetUseCase
import cn.junmov.mirror.budget.domain.UpdateBudgetUseCase
import cn.junmov.mirror.core.data.entity.Account
import cn.junmov.mirror.core.utility.MoneyUtils
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class BudgetDetailViewModel @ViewModelInject constructor(
    private val flowBudget: FlowBudgetUseCase,
    private val updateBudget: UpdateBudgetUseCase
) : ViewModel() {

    private val _category = MutableLiveData<Account>()

    val useAble = MutableLiveData<String>()

    val used = MutableLiveData<String>()

    val total = MutableLiveData<String>()


    fun loadData(categoryId: Long) {
        viewModelScope.launch {
            flowBudget(categoryId).collectLatest {
                _category.value = it
                useAble.value = MoneyUtils.centToYuan(it.base + it.inflow - it.outflow)
                used.value = MoneyUtils.centToYuan(it.outflow)
                total.value = MoneyUtils.centToYuan(it.base + it.inflow)
            }
        }
    }

    fun submitBudget(newBudget: String) {
        val budget = MoneyUtils.yuanToCent(newBudget)
        val category = _category.value ?: return
        viewModelScope.launch {
            updateBudget(category, budget)
        }
    }
}