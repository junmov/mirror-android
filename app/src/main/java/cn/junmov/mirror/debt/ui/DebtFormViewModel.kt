package cn.junmov.mirror.debt.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import cn.junmov.mirror.core.data.db.entity.Debt
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

    val updated = MutableLiveData<Boolean>()

    fun submit() {
        val currentSummary = summary.value ?: return
        val currentCapital = capital.value ?: return
        val currentCount = count.value ?: return
        val currentStartAt = startAt.value ?: return
        val currentInterest = interest.value ?: return
        val debt = Debt(
            id = SnowFlakeUtil.genId(), summary = currentSummary,
            capital = MoneyUtils.yuanToCent(currentCapital),
            count = currentCount.toInt(), startAt = TimeUtils.stringToDate(currentStartAt),
            capitalRepay = 0, interestRepay = 0, countRepay = 0, settled = false
        )

        viewModelScope.launch {
            createAgingDebt(debt, MoneyUtils.yuanToCent(currentInterest))
            updated.value = true
        }

    }

}