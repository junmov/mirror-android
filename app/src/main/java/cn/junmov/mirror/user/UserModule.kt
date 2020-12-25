package cn.junmov.mirror.user

import cn.junmov.mirror.core.data.remote.MirrorService
import cn.junmov.mirror.core.data.store.ProfileDataStore
import cn.junmov.mirror.user.data.UserRepository
import cn.junmov.mirror.user.data.UserRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UserModule {
    @Provides
    @Singleton
    fun userRepository(dataStore: ProfileDataStore, service: MirrorService): UserRepository =
        UserRepositoryImpl(dataStore, service)
}