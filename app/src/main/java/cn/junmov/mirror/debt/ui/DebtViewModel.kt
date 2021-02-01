package cn.junmov.mirror.debt.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import cn.junmov.mirror.core.data.db.entity.Debt
import cn.junmov.mirror.debt.domain.FlowAllDebtUseCase

class DebtViewModel @ViewModelInject constructor(
    private val flowAllDebt: FlowAllDebtUseCase
) : ViewModel() {

    private val _debts: LiveData<List<Debt>> = flowAllDebt().asLiveData()

    private val showSettledDebt = MutableLiveData(false)

    val debts: LiveData<List<Debt>> = showSettledDebt.switchMap { show ->
        if (show) {
            _debts
        } else {
            _debts.map { list -> list.filter { !it.settled } }
        }
    }

    fun toggleFilter() {
        val show = showSettledDebt.value ?: false
        showSettledDebt.value = !show
    }
}