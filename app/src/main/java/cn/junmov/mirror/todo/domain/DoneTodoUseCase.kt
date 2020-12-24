package cn.junmov.mirror.todo.domain

import cn.junmov.mirror.core.data.db.dao.TodoDao
import cn.junmov.mirror.core.data.db.entity.Todo
import java.time.LocalDateTime

class DoneTodoUseCase(private val dao: TodoDao) {
    suspend operator fun invoke(todo: Todo) {
        val now = LocalDateTime.now()
        todo.done(now)
        dao.update(todo)
    }
}
