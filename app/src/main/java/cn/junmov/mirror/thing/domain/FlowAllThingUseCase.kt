package cn.junmov.mirror.thing.domain

import cn.junmov.mirror.core.data.dao.ThingDao
import cn.junmov.mirror.core.data.entity.Thing
import kotlinx.coroutines.flow.Flow

class FlowAllThingUseCase(private val dao: ThingDao) {
    operator fun invoke(): Flow<List<Thing>> {
        return dao.flowAllThing()
    }
}