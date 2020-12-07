package cn.junmov.mirror.todo.domain

import cn.junmov.mirror.core.data.dao.TodoDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.time.LocalDateTime

class ChangeTodoStateUseCase(private val dao: TodoDao) {

    suspend operator fun invoke(id: Long, enable: Boolean) {
        withContext(Dispatchers.IO) {
            val now = LocalDateTime.now()
            dao.changeTodoState(id, enable, now)
        }
    }
}