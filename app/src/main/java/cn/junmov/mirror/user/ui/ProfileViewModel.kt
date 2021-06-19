package cn.junmov.mirror.user.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import cn.junmov.mirror.account.domain.BudgetMonthlyUseCase
import cn.junmov.mirror.core.utility.TimeUtils
import cn.junmov.mirror.user.domain.UserRepository
import kotlinx.coroutines.launch
import java.time.LocalDate

class ProfileViewModel @ViewModelInject constructor(
    private val repository: UserRepository,
    private val budgetMonthly: BudgetMonthlyUseCase
) : ViewModel() {

    val isSigned: LiveData<Boolean> = repository.flowSign().asLiveData()

    val username: LiveData<String> = repository.flowUserName().asLiveData()

    val userNickname: LiveData<String> = repository.flowUserNickName().asLiveData()

    val budgetMonth: LiveData<String> = repository.flowBudgetMonth().asLiveData()

    val isSigning = MutableLiveData(false)

    val inputUserName = MutableLiveData<String>()
    val inputPassword = MutableLiveData<String>()
    val inputIpAddress = MutableLiveData<String>()

    fun ordinarySignIn() {
        val username = inputUserName.value ?: return
        val password = inputPassword.value ?: return
        val ipAddress = inputIpAddress.value ?: return
        viewModelScope.launch {
            isSigning.value = true
            repository.signIn(username, password, ipAddress)
            isSigning.value = false
        }
    }

    fun guestSignIn() {
        viewModelScope.launch {
            isSigning.value = true
            repository.createGuest()
            isSigning.value = false
        }
    }

    fun checkBudgetMonth(last: String) {
        val now = LocalDate.now()
        val old = TimeUtils.stringToDate(last)
        if (now.year == old.year && now.month == old.month) return
        viewModelScope.launch {
            budgetMonthly()
            repository.updateBudgetMonth()
        }
    }

}