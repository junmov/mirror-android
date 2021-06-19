package cn.junmov.mirror.account.ui.budget

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import cn.junmov.mirror.account.domain.FlowAllBudgetUseCase
import cn.junmov.mirror.account.domain.AdjustBudgetUseCase
import cn.junmov.mirror.core.data.db.entity.Account
import cn.junmov.mirror.core.utility.MoneyUtils
import kotlinx.coroutines.launch

class BudgetViewModel @ViewModelInject constructor(
    private val flowAllBudget: FlowAllBudgetUseCase,
    val adjustBudget: AdjustBudgetUseCase
) : ViewModel() {

    val budgets: LiveData<List<Account>> = flowAllBudget().asLiveData()

    fun submitBudget(categoryId: Long, budgetStr: String) {
        if (!MoneyUtils.isFormat(budgetStr)) return
        viewModelScope.launch {
            adjustBudget(categoryId, budgetStr)
        }
    }
}