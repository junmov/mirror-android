package cn.junmov.mirror.account.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import cn.junmov.mirror.account.domain.FlowAllBalanceAccountUseCase

class BalanceAccountViewModel @ViewModelInject constructor(
    private val flowAllBalanceAccount: FlowAllBalanceAccountUseCase
) : ViewModel() {
    val accounts = flowAllBalanceAccount().asLiveData()
}