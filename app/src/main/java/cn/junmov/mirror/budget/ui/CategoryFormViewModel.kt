package cn.junmov.mirror.budget.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import cn.junmov.mirror.budget.domain.FlowAllFirstBudgetUseCase
import cn.junmov.mirror.core.data.model.AccountType
import cn.junmov.mirror.core.data.db.entity.Account
import cn.junmov.mirror.core.utility.SnowFlakeUtil
import cn.junmov.mirror.wallet.domain.CreateAccountUseCase
import kotlinx.coroutines.launch
import java.time.LocalDateTime

class CategoryFormViewModel @ViewModelInject constructor(
    private val flowAllFirstCategory: FlowAllFirstBudgetUseCase,
    private val createCategory: CreateAccountUseCase
) : ViewModel() {

    val firstCategory: LiveData<List<Account>> = flowAllFirstCategory().asLiveData()

    private val inputParent = MutableLiveData<Account>()
    val inputType = MutableLiveData<AccountType>()
    val inputName = MutableLiveData<String>()

    val updated = MutableLiveData<Boolean>()

    fun selectType(type: AccountType) {
        inputType.value = type
    }

    fun selectParent(category: Account) {
        inputParent.value = category
        inputType.value = category.type
    }

    fun submitCategory() {
        val currentType = inputType.value ?: return
        val currentName = inputName.value ?: return
        val currentParent = inputParent.value
        viewModelScope.launch {
            val fullName: String
            val parentId: Long
            val tradAble: Boolean
            if (currentParent == null) {
                fullName = currentName
                parentId = 0L
                tradAble = false
            } else {
                fullName = "${currentParent.fullName}:${currentName}"
                parentId = currentParent.id
                tradAble = true
            }
            val now = LocalDateTime.now()
            val category = Account(
                id = SnowFlakeUtil.genId(), name = currentName,
                fullName = fullName, parentId = parentId, type = currentType,
                tradAble = tradAble, tradeCount = 0,
                base = 0, inflow = 0, outflow = 0,
                createAt = now, modifiedAt = now, deleted = false,
            )
            createCategory(category)
            updated.value = true
        }
    }
}