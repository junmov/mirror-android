package cn.junmov.mirror.user

import cn.junmov.mirror.core.data.db.MirrorDatabase
import cn.junmov.mirror.core.data.remote.MirrorService
import cn.junmov.mirror.core.data.store.ProfileDataStore
import cn.junmov.mirror.user.domain.UserRepository
import cn.junmov.mirror.user.domain.SyncRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MineModule {
    @Provides
    @Singleton
    fun userRepository(dataStore: ProfileDataStore, service: MirrorService): UserRepository =
        UserRepository(dataStore, service)

    @Provides
    @Singleton
    fun mineRepository(
        service: MirrorService, dataStore: ProfileDataStore, database: MirrorDatabase
    ): SyncRepository = SyncRepository(dataStore, service, database.mineDao())
}