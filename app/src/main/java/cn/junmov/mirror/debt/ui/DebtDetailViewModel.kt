package cn.junmov.mirror.debt.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import cn.junmov.mirror.core.data.db.entity.Debt
import cn.junmov.mirror.core.data.db.entity.Repay
import cn.junmov.mirror.core.utility.MoneyUtils
import cn.junmov.mirror.debt.domain.FlowDebtRepaysUseCase
import cn.junmov.mirror.debt.domain.FlowDebtUseCase

class DebtDetailViewModel @ViewModelInject constructor(
    private val flowDebt: FlowDebtUseCase,
    private val flowRepays: FlowDebtRepaysUseCase,
) : ViewModel() {

    private val _debtId = MutableLiveData<Long>()

    val debt: LiveData<Debt> = _debtId.switchMap { flowDebt(it).asLiveData() }

    val repays: LiveData<List<Repay>> = _debtId.switchMap { flowRepays(it).asLiveData() }

    val noSettled: LiveData<String> = repays.map { list ->
        val interest = list.filter { !it.settled }.sumOf { it.interest }
        "待还利息:${MoneyUtils.centToYuan(interest)}"
    }
    val amount: LiveData<String> = debt.map { MoneyUtils.centToYuan(it.capital - it.capitalRepay) }

    val settled: LiveData<String> = debt.map {
        "已还本金:${MoneyUtils.centToYuan(it.capitalRepay)},利息:${MoneyUtils.centToYuan(it.interestRepay)}"
    }

    fun loadData(id: Long) {
        if (id == 0L) return
        _debtId.value = id
    }

}