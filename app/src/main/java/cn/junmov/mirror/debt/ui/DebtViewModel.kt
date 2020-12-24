package cn.junmov.mirror.debt.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import cn.junmov.mirror.core.data.db.entity.Debt
import cn.junmov.mirror.debt.domain.FlowAllDebtUseCase

class DebtViewModel @ViewModelInject constructor(
    private val flowAllDebt: FlowAllDebtUseCase
) : ViewModel() {

    val debts:LiveData<List<Debt>> = flowAllDebt().asLiveData()

}