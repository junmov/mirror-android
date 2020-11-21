package cn.junmov.mirror.account.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cn.junmov.mirror.R
import cn.junmov.mirror.account.domain.AdjustAccountBalanceUseCase
import cn.junmov.mirror.account.domain.FlowAccountWithChildrenUseCase
import cn.junmov.mirror.core.data.entity.Account
import cn.junmov.mirror.core.utility.MoneyUtils
import cn.junmov.mirror.core.utility.TimeUtils
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class AccountDetailViewModel @ViewModelInject constructor(
    private val adjustBalance: AdjustAccountBalanceUseCase,
    private val flowAccount: FlowAccountWithChildrenUseCase
) : ViewModel() {
    val account = MutableLiveData<Account>()
    val children = MutableLiveData<List<Account>>()

    val balance = MutableLiveData<String>()
    val createAt = MutableLiveData<String>()

    val message = MutableLiveData<Int>()

    fun loadData(id: Long) {
        viewModelScope.launch {
            flowAccount(id).collectLatest {
                account.value = it.parent
                children.value = it.children
                balance.value = MoneyUtils.centToYuan(it.parent.balance)
                createAt.value = TimeUtils.dateToString(it.parent.createAt.toLocalDate())
            }
        }
    }

    fun adjustBalance(balance: String) {
        if (!MoneyUtils.isFormat(balance)) {
            message.value = R.string.error_balance_not_format
            return
        }
        viewModelScope.launch {
            account.value?.let {
                adjustBalance(it, MoneyUtils.yuanToCent(balance))
            }
        }
    }
}