package cn.junmov.mirror.home.domain

import cn.junmov.mirror.core.data.dao.TodoDao
import cn.junmov.mirror.core.data.entity.Todo
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

class FlowAllTodayDoneTodoUseCase(private val dao: TodoDao) {
    operator fun invoke(): Flow<List<Todo>> {
        return dao.flowAllRunAt(LocalDate.now())
    }
}
