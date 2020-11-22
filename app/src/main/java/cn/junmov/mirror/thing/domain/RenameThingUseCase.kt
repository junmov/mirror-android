package cn.junmov.mirror.thing.domain

import cn.junmov.mirror.core.data.dao.ThingDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.time.LocalDateTime

class RenameThingUseCase(private val dao: ThingDao) {
    suspend operator fun invoke(thingId: Long, name: String) {
        withContext(Dispatchers.IO) {
            dao.renameTransaction(thingId, name, LocalDateTime.now())
        }
    }
}