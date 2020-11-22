package cn.junmov.mirror.thing

import cn.junmov.mirror.core.data.MirrorDatabase
import cn.junmov.mirror.thing.domain.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
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