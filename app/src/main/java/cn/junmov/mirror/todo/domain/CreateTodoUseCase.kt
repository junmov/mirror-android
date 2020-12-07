package cn.junmov.mirror.todo.domain

import cn.junmov.mirror.core.data.dao.TodoDao
import cn.junmov.mirror.core.data.entity.Todo


class CreateTodoUseCase(private val dao: TodoDao) {
    suspend operator fun invoke(todo: Todo) {
        dao.insert(todo)
    }
}