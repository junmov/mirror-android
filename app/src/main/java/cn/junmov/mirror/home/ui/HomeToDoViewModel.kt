package cn.junmov.mirror.home.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import cn.junmov.mirror.core.data.db.entity.Todo
import cn.junmov.mirror.todo.domain.DoneTodoUseCase
import cn.junmov.mirror.home.domain.FlowAllTodayDoneTodoUseCase
import kotlinx.coroutines.launch

class HomeToDoViewModel @ViewModelInject constructor(
    private val flowAllTodo: FlowAllTodayDoneTodoUseCase,
    private val doneTodo: DoneTodoUseCase
) : ViewModel() {

    val toDoList: LiveData<List<Todo>> = flowAllTodo().asLiveData()

    fun done(todo: Todo) {
        viewModelScope.launch {
            doneTodo(todo)
        }
    }
}