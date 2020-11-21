package cn.junmov.mirror.account.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cn.junmov.mirror.account.domain.FlowAllNoLeafAccountUseCase
import cn.junmov.mirror.core.data.entity.Account
import cn.junmov.mirror.core.utility.MoneyUtils
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class AccountViewModel @ViewModelInject constructor(
    private val flowAllParentAccount: FlowAllNoLeafAccountUseCase
)  : ViewModel() {
    val accounts = MutableLiveData<List<Account>>()

    val netAsset = MutableLiveData<String>()
    val totalAssets = MutableLiveData<String>()
    val totalLiabilities = MutableLiveData<String>()

    fun loadData() {
        viewModelScope.launch {
            flowAllParentAccount().collectLatest { list ->
                var asset = 0
                var liability = 0
                list.forEach {
                    if (it.type.isAsset()) {
                        asset += it.balance
                    } else if (it.type.isLiability()) {
                        liability += it.balance
                    }
                }
                accounts.value = list
                netAsset.value = MoneyUtils.centToYuan(asset - liability)
                totalAssets.value = MoneyUtils.centToYuan(asset)
                totalLiabilities.value = MoneyUtils.centToYuan(liability)
            }
        }
    }
}