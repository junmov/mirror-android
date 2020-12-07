package cn.junmov.mirror.wallet.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cn.junmov.mirror.wallet.domain.CreateAccountUseCase
import cn.junmov.mirror.core.data.AccountType
import cn.junmov.mirror.core.data.entity.Account
import cn.junmov.mirror.core.utility.MoneyUtils
import cn.junmov.mirror.core.utility.SnowFlakeUtil
import kotlinx.coroutines.launch

class WalletFormViewModel @ViewModelInject constructor(
    private val createWallet: CreateAccountUseCase
) : ViewModel() {

    val inputType = MutableLiveData<AccountType>()
    val inputName = MutableLiveData<String>()
    val inputBalance = MutableLiveData<String>()

    val updated = MutableLiveData<Boolean>()

    fun selectType(type: AccountType) {
        inputType.value = type
    }

    fun submitWallet() {
        val currentName = inputName.value ?: return
        val currentType = inputType.value ?: return
        val currentBalance = inputBalance.value ?: return
        val balance = MoneyUtils.yuanToCent(currentBalance)
        viewModelScope.launch {
            val wallet = Account(
                id = SnowFlakeUtil.genId(), name = currentName, type = currentType,
                tradeCount = 0, base = balance, fullName = currentName,
                parentId = 0, tradAble = true, inflow = 0, outflow = 0
            )
            createWallet(wallet)
            updated.value = true
        }
    }
}