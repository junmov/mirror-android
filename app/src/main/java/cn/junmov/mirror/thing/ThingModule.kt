package cn.junmov.mirror.thing

import cn.junmov.mirror.core.data.db.MirrorDatabase
import cn.junmov.mirror.thing.domain.CreateThingUseCase
import cn.junmov.mirror.thing.domain.FlowAllThingUseCase
import cn.junmov.mirror.thing.domain.PagingVoucherByThingUseCase
import cn.junmov.mirror.thing.domain.RenameThingUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ThingModule {

    @Singleton
    @Provides
    fun createThingUseCase(database: MirrorDatabase) =
        CreateThingUseCase(database.thingDao())

    @Singleton
    @Provides
    fun flowAllThingUseCase(database: MirrorDatabase) =
        FlowAllThingUseCase(database.thingDao())

    @Singleton
    @Provides
    fun pagingVoucherByThingUseCase(database: MirrorDatabase) =
        PagingVoucherByThingUseCase(database.thingDao())

    @Singleton
    @Provides
    fun renameThingUseCase(database: MirrorDatabase) =
        RenameThingUseCase(database.thingDao())

}