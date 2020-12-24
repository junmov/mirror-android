package cn.junmov.mirror.core.data.db.dao

import androidx.room.Insert
import androidx.room.Update

interface BaseDao<T> {

    @Insert
    suspend fun insert(vararg entity: T)

    @Update
    suspend fun update(vararg entity: T)
}
