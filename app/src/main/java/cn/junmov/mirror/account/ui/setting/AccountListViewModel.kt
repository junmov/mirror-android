package cn.junmov.mirror.account.ui.setting

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import cn.junmov.mirror.account.domain.FlowAllAccountUseCase
import cn.junmov.mirror.core.data.db.entity.Account

class AccountListViewModel @ViewModelInject constructor(
    private val flowAllAccount: FlowAllAccountUseCase
) : ViewModel() {

    val accounts: LiveData<List<Account>> = flowAllAccount().asLiveData()

}