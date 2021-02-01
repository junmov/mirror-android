package cn.junmov.mirror.debt.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import cn.junmov.mirror.core.data.db.entity.Repay
import cn.junmov.mirror.core.utility.MoneyUtils
import cn.junmov.mirror.core.utility.TimeUtils
import cn.junmov.mirror.debt.domain.FlowRepaysUseCase
import cn.junmov.mirror.debt.domain.PayDateRepayUseCase
import kotlinx.coroutines.launch
import java.time.LocalDate

class RepayDetailViewModel @ViewModelInject constructor(
    private val flowRepays: FlowRepaysUseCase,
    private val payDateRepay: PayDateRepayUseCase
) : ViewModel() {

    private val _dateAt = MutableLiveData<LocalDate>()

    val repays: LiveData<List<Repay>> = _dateAt.switchMap { flowRepays(it).asLiveData() }

    val dateAt: LiveData<String> = _dateAt.map { "${TimeUtils.dateToString(it)}应还" }

    val total: LiveData<String> = repays.map { list ->
        var amount = 0
        list.forEach {
            amount += it.capital
            amount += it.interest
        }
        MoneyUtils.centToYuan(amount)
    }

    fun loadData(dateAt: String) {
        _dateAt.value = TimeUtils.stringToDate(dateAt)
    }

    fun submitSettled(dateAt: String) {
        viewModelScope.launch { payDateRepay(TimeUtils.stringToDate(dateAt)) }
    }
}