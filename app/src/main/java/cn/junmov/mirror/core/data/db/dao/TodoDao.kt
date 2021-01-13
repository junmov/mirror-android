package cn.junmov.mirror.core.data.db.dao

import androidx.room.Dao
import androidx.room.Query
import cn.junmov.mirror.core.data.db.entity.Todo
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate
import java.time.LocalDateTime

@Dao
interface TodoDao : BaseDao<Todo> {
    @Query("select * from todo where is_deleted = 0 order by run_at")
    fun flowAllTodo(): Flow<List<Todo>>

    @Query("select * from todo where is_enabled = 1 and run_at <= :runAt and is_deleted = 0")
    fun flowAllRunAt(runAt: LocalDate): Flow<List<Todo>>
}