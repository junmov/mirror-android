package cn.junmov.mirror.todo.domain

import cn.junmov.mirror.core.data.db.dao.TodoDao
import cn.junmov.mirror.core.data.db.entity.Todo
import java.time.LocalDateTime

class ChangeTodoStateUseCase(private val dao: TodoDao) {

    suspend operator fun invoke(todo: Todo, enable: Boolean) {
        val now = LocalDateTime.now()
        todo.enabled = enable
        todo.modifiedAt = now
        if (enable) {
            todo.runAt = now.toLocalDate()
        }
        dao.update(todo)
    }
}