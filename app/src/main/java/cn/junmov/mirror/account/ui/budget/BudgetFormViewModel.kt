package cn.junmov.mirror.account.ui.budget

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import cn.junmov.mirror.account.domain.AdjustBudgetUseCase
import cn.junmov.mirror.account.domain.FlowAllAccountByTypeUseCase
import cn.junmov.mirror.core.data.db.entity.Account
import cn.junmov.mirror.core.data.model.AccountType
import kotlinx.coroutines.launch

class BudgetFormViewModel @ViewModelInject constructor(
    private val flowAllAccount: FlowAllAccountByTypeUseCase,
    private val updateBudget: AdjustBudgetUseCase
) : ViewModel() {

    val expends: LiveData<List<Account>> = flowAllAccount(AccountType.CONSUME).asLiveData()

    private val inputAccount = MutableLiveData<Account>()
    val inputAmount = MutableLiveData<String>()
    val updated = MutableLiveData(false)

    fun selectAccount(account: Account) {
        inputAccount.value = account
    }

    fun submitBudget() {
        val account = inputAccount.value ?: return
        val amount = inputAmount.value ?: return
        viewModelScope.launch {
            updateBudget(account.id, amount)
            updated.value = true
        }
    }
}