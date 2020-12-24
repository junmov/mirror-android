package cn.junmov.mirror.sync

import cn.junmov.mirror.core.data.db.MirrorDatabase
import cn.junmov.mirror.core.data.remote.MirrorService
import cn.junmov.mirror.core.data.store.ProfileDataStore
import cn.junmov.mirror.sync.data.SyncRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SyncModule {

    @Provides
    @Singleton
    fun mineRepository(
        service: MirrorService, dataStore: ProfileDataStore, database: MirrorDatabase
    ): SyncRepository = SyncRepository(dataStore, service, database.mineDao())

}