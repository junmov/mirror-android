package cn.junmov.mirror.thing.domain

import cn.junmov.mirror.core.data.db.dao.ThingDao
import cn.junmov.mirror.core.data.db.entity.Thing
import cn.junmov.mirror.core.utility.SnowFlakeUtil
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.time.LocalDateTime

class CreateThingUseCase(private val dao: ThingDao) {
    suspend operator fun invoke(name: String) {
        val now = LocalDateTime.now()
        val thing = Thing(id = SnowFlakeUtil.genId(), name = name, createAt = now, modifiedAt = now)
        withContext(Dispatchers.IO) {
            dao.insert(thing)
        }
    }
}