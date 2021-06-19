package cn.junmov.mirror.account.ui.setting

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cn.junmov.mirror.account.domain.CreateAccountUseCase
import cn.junmov.mirror.core.data.model.AccountType
import cn.junmov.mirror.core.data.db.entity.Account
import cn.junmov.mirror.core.utility.SnowFlakeUtil
import kotlinx.coroutines.launch

class AccountFormViewModel @ViewModelInject constructor(
    private val createAccount: CreateAccountUseCase
) : ViewModel() {

    val inputType = MutableLiveData<AccountType>()
    val inputName = MutableLiveData<String>()

    val updated = MutableLiveData<Boolean>()

    fun selectType(type: AccountType) {
        inputType.value = type
    }

    fun submitAccount() {
        val currentName = inputName.value ?: return
        val currentType = inputType.value ?: return
        viewModelScope.launch {
            val wallet = Account(
                id = SnowFlakeUtil.genId(), name = currentName, type = currentType,
            )
            createAccount(wallet)
            updated.value = true
        }
    }
}