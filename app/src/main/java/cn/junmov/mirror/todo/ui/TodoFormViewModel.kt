package cn.junmov.mirror.todo.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cn.junmov.mirror.core.data.PeriodType
import cn.junmov.mirror.core.data.entity.Todo
import cn.junmov.mirror.core.utility.SnowFlakeUtil
import cn.junmov.mirror.core.utility.TimeUtils
import cn.junmov.mirror.todo.domain.CreateTodoUseCase
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalDateTime

class TodoFormViewModel @ViewModelInject constructor(
    private val createTodo: CreateTodoUseCase
) : ViewModel() {

    val inputSummary = MutableLiveData<String>()
    val inputPeriod = MutableLiveData<PeriodType>()
    val inputDoneTotal = MutableLiveData<String>()
    val inputFirstRun = MutableLiveData<String>()

    init {
        inputPeriod.value = PeriodType.DAILY
        inputDoneTotal.value = "0"
        inputFirstRun.value = TimeUtils.dateToString(LocalDate.now())
    }

    val updated = MutableLiveData<Boolean>()

    fun selectPeriod(type: PeriodType) {
        inputPeriod.value = type
    }

    fun submit() {
        val currentSummary = inputSummary.value ?: return
        val currentPeriod = inputPeriod.value ?: return
        val currentDoneTotal = inputDoneTotal.value ?: return
        val currentFirstRun = inputFirstRun.value ?: return
        viewModelScope.launch {
            val now = LocalDateTime.now()
            val todo = Todo(
                id = SnowFlakeUtil.genId(), summary = currentSummary, isEnabled = true,
                period = currentPeriod, runAt = TimeUtils.stringToDate(currentFirstRun),
                doneTimes = 0, doneTotal = currentDoneTotal.toInt(),
                createAt = now, modifiedAt = now, isDeleted = false
            )
            createTodo(todo)
            updated.value = true
        }
    }

}