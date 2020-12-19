package cn.junmov.mirror.sync

import android.content.Context
import cn.junmov.mirror.core.data.MirrorDatabase
import cn.junmov.mirror.sync.api.MirrorService
import cn.junmov.mirror.sync.data.SyncRepository
import cn.junmov.mirror.sync.data.local.ProfileDataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SyncModule {

    @Provides
    @Singleton
    fun mirrorService(): MirrorService = MirrorService.create()

    @Provides
    @Singleton
    fun profileDataStore(@ApplicationContext ctx: Context): ProfileDataStore = ProfileDataStore(ctx)

    @Provides
    @Singleton
    fun mineRepository(
        service: MirrorService, dataStore: ProfileDataStore, database: MirrorDatabase
    ): SyncRepository = SyncRepository(dataStore, service, database.mineDao())

}