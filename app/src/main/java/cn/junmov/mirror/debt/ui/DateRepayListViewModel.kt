package cn.junmov.mirror.debt.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import cn.junmov.mirror.debt.data.DateRepay
import cn.junmov.mirror.debt.domain.FlowDateRepayUseCase

class DateRepayListViewModel @ViewModelInject constructor(
    private val flowAllDateRepay: FlowDateRepayUseCase
) : ViewModel() {

    val dateRepays:LiveData<List<DateRepay>> = flowAllDateRepay().asLiveData()

}
