package cn.junmov.mirror.account.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import cn.junmov.mirror.account.domain.CreateAccountUseCase
import cn.junmov.mirror.account.domain.FlowAllAccountByLeafUseCase
import cn.junmov.mirror.core.data.AccountType
import cn.junmov.mirror.core.data.entity.Account
import cn.junmov.mirror.core.utility.SnowFlakeUtil
import kotlinx.coroutines.launch

class AccountFormViewModel @ViewModelInject constructor(
    private val flowAllNoLeafAccount: FlowAllAccountByLeafUseCase,
    private val createAccount: CreateAccountUseCase
) : ViewModel() {

    val parentAccounts = flowAllNoLeafAccount().asLiveData()

    val accountType = MutableLiveData<AccountType>()
    val accountName = MutableLiveData<String>()
    private val accountParent = MutableLiveData<Account>()
    val leaf = MutableLiveData<Boolean>(false)

    val nameOk: LiveData<Boolean> = accountName.map { it.isNotBlank() }
    val typeOk: LiveData<Boolean> = accountType.map { it != null }

    val updated = MutableLiveData<Boolean>()

    fun selectType(type: AccountType) {
        accountType.value = type
    }

    fun selectParent(account: Account) {
        accountParent.value = account
        accountType.value = account.type
    }

    fun submitAccount() {
        val currentName = accountName.value ?: return
        val currentType = accountType.value ?: return
        val currentIsLeaf = leaf.value ?: return
        val currentParent = accountParent.value
        if (currentParent == null && currentIsLeaf) {
            if (!currentType.hasNormalBalance()) {
                return
            }
        }
        viewModelScope.launch {
            val fullMame: String
            val parentId: Long
            if (currentParent == null) {
                fullMame = currentName
                parentId = 0L
            } else {
                fullMame = "${currentParent.fullName}:$currentName"
                parentId = currentParent.id
            }

            val account = Account(
                id = SnowFlakeUtil.genId(), name = currentName, type = currentType,
                sortKey = 0, balance = 0, fullName = fullMame,
                parentId = parentId, isLeaf = currentIsLeaf
            )
            createAccount(account)
            updated.value = true
        }
    }
}