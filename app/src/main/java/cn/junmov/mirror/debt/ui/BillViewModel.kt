package cn.junmov.mirror.debt.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import cn.junmov.mirror.debt.data.DateRepay
import cn.junmov.mirror.debt.domain.FlowDateRepayUseCase
import cn.junmov.mirror.debt.domain.PayDateRepayUseCase
import kotlinx.coroutines.launch
import java.time.LocalDate

class BillViewModel @ViewModelInject constructor(
    private val flowAllDateRepay: FlowDateRepayUseCase
) : ViewModel() {

    val dateRepays:LiveData<List<DateRepay>> = flowAllDateRepay().asLiveData()

}
