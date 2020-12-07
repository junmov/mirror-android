package cn.junmov.mirror.home.domain

import cn.junmov.mirror.core.data.dao.TodoDao
import cn.junmov.mirror.core.data.entity.Todo
import java.time.LocalDateTime

class DoneTodoUseCase(private val dao: TodoDao) {
    suspend operator fun invoke(todo: Todo) {
        val now = LocalDateTime.now()
        todo.done(now)
        dao.update(todo)
    }
}
