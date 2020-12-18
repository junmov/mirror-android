package cn.junmov.mirror.debt.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import cn.junmov.mirror.core.data.entity.Account
import cn.junmov.mirror.core.data.entity.Debt
import cn.junmov.mirror.core.utility.MoneyUtils
import cn.junmov.mirror.core.utility.SnowFlakeUtil
import cn.junmov.mirror.core.utility.TimeUtils
import cn.junmov.mirror.debt.domain.CreateDebtUseCase
import cn.junmov.mirror.debt.domain.FlowAllDebtAccountUseCase
import kotlinx.coroutines.launch
import java.time.LocalDate

class DebtFormViewModel @ViewModelInject constructor(
    private val createAgingDebt: CreateDebtUseCase,
    private val flowDebtAccount: FlowAllDebtAccountUseCase,
) : ViewModel() {

    val accounts = flowDebtAccount().asLiveData()

    val summary = MutableLiveData<String>()
    val capital = MutableLiveData<String>()
    val count = MutableLiveData<String>()
    val startAt = MutableLiveData(TimeUtils.dateToString(LocalDate.now().plusMonths(1)))
    val interest = MutableLiveData<String>()
    private val account = MutableLiveData<Account>()

    val updated = MutableLiveData<Boolean>()

    fun selectAccount(a: Account) {
        account.value = a
    }

    fun submit() {
        val currentSummary = summary.value ?: return
        val currentCapital = capital.value ?: return
        val currentCount = count.value ?: return
        val currentStartAt = startAt.value ?: return
        val currentInterest = interest.value ?: return
        val currentAccount = account.value ?: return

        val debt = Debt(
            id = SnowFlakeUtil.genId(), summary = currentSummary,
            accountId = currentAccount.id, capital = MoneyUtils.yuanToCent(currentCapital),
            interest = MoneyUtils.yuanToCent(currentInterest),
            count = currentCount.toInt(), startAt = TimeUtils.stringToDate(currentStartAt),
            capitalRepay = 0, interestRepay = 0, countRepay = 0, settled = false
        )

        viewModelScope.launch {
            createAgingDebt(debt)
            updated.value = true
        }

    }

}