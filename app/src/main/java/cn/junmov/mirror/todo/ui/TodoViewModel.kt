package cn.junmov.mirror.todo.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import cn.junmov.mirror.core.data.db.entity.Todo
import cn.junmov.mirror.todo.domain.ChangeTodoStateUseCase
import cn.junmov.mirror.todo.domain.FlowAllTodoUseCase
import kotlinx.coroutines.launch

class TodoViewModel @ViewModelInject constructor(
    private val flowAllTodo: FlowAllTodoUseCase,
    private val changeState: ChangeTodoStateUseCase
) : ViewModel() {

    val todoList: LiveData<List<Todo>> = flowAllTodo().asLiveData()

    fun changeScheduleState(todoId: Long, enable: Boolean) {
        viewModelScope.launch {
            changeState(todoId, enable)
        }
    }
}