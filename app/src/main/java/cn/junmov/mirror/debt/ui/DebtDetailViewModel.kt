package cn.junmov.mirror.debt.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import cn.junmov.mirror.core.data.db.entity.Debt
import cn.junmov.mirror.core.data.db.entity.Repay
import cn.junmov.mirror.core.utility.MoneyUtils
import cn.junmov.mirror.debt.domain.FlowDebtInfoUseCase
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class DebtDetailViewModel @ViewModelInject constructor(
    private val flowDebtInfo: FlowDebtInfoUseCase
) : ViewModel() {

    val debt = MutableLiveData<Debt>()
    val items = MutableLiveData<List<Repay>>()

    val noSettled = MutableLiveData<String>()
    val amount = MutableLiveData<String>()
    val settled = MutableLiveData<String>()

    fun loadData(id: Long) {
        if (id == 0L) return
        viewModelScope.launch {
            flowDebtInfo(id).collectLatest { info ->
                debt.value = info.debt
                items.value = info.items.filter { !it.deleted }
                amount.value = MoneyUtils.centToYuan(info.debt.capital - info.debt.capitalRepay)
                noSettled.value = "待还利息:${
                    MoneyUtils.centToYuan(info.debt.interest - info.debt.interestRepay)
                },剩${info.debt.count - info.debt.countRepay}期还清"
                settled.value =
                    "已还:${MoneyUtils.centToYuan(info.debt.capitalRepay + info.debt.interestRepay)}(含本金${
                        MoneyUtils.centToYuan(info.debt.capitalRepay)
                    },利息${MoneyUtils.centToYuan(info.debt.interestRepay)})"
            }
        }
    }

}