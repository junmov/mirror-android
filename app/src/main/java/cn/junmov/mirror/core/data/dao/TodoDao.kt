package cn.junmov.mirror.core.data.dao

import androidx.room.Dao
import androidx.room.Query
import cn.junmov.mirror.core.data.entity.Todo
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate
import java.time.LocalDateTime

@Dao
interface TodoDao : BaseDao<Todo> {
    @Query("select * from todo where is_deleted = 0 order by run_at")
    fun flowAllTodo(): Flow<List<Todo>>

    @Query("update todo set is_enabled = :enable, modified_at = :modified where row_id = :id")
    suspend fun changeTodoState(id: Long, enable: Boolean, modified: LocalDateTime)

    @Query("select * from todo where run_at <= :runAt and is_deleted = 0")
    fun flowAllRunAt(runAt: LocalDate): Flow<List<Todo>>
}