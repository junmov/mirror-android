package cn.junmov.mirror.todo.domain

import cn.junmov.mirror.core.data.db.dao.TodoDao
import cn.junmov.mirror.core.data.db.entity.Todo
import kotlinx.coroutines.flow.Flow

class FlowAllTodoUseCase(private val dao: TodoDao) {
    operator fun invoke(): Flow<List<Todo>> {
        return dao.flowAllTodo()
    }
}