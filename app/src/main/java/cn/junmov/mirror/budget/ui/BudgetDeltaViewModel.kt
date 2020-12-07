package cn.junmov.mirror.budget.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import cn.junmov.mirror.budget.domain.FlowBudgetUseCase
import cn.junmov.mirror.budget.domain.UpdateBudgetUseCase
import cn.junmov.mirror.core.data.model.Category
import cn.junmov.mirror.core.utility.MoneyUtils
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class BudgetDeltaViewModel @ViewModelInject constructor(
    private val flowBudget: FlowBudgetUseCase,
    private val updateBudget: UpdateBudgetUseCase
) : ViewModel() {

    private val _category = MutableLiveData<Category>()

    val useAble: LiveData<String> = _category.map { MoneyUtils.centToYuan(it.budgetUseAble) }

    val used: LiveData<String> = _category.map { MoneyUtils.centToYuan(it.budgetUsed) }

    val total: LiveData<String> = _category.map { MoneyUtils.centToYuan(it.budgetTotal) }

    val avgUsed: LiveData<String> = _category.map { MoneyUtils.centToYuan(it.avgUsed) }

    val usable: LiveData<String> = _category.map { MoneyUtils.centToYuan(it.avgUseAble) }


    fun loadData(categoryId: Long) {
        viewModelScope.launch {
            flowBudget(categoryId).collectLatest {
                _category.value = it
            }
        }
    }

    fun setupBudget(text: String) {
        val budget = MoneyUtils.yuanToCent(text)
        val category = _category.value ?: return
        viewModelScope.launch {
            updateBudget(category, budget)
        }
    }
}