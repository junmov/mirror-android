package cn.junmov.mirror.core.data.dao

import androidx.room.Dao
import cn.junmov.mirror.core.data.entity.Thing

@Dao
interface ThingDao : BaseDao<Thing> {
}