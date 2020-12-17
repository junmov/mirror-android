package cn.junmov.mirror.mine

import android.content.Context
import cn.junmov.mirror.core.data.MirrorDatabase
import cn.junmov.mirror.mine.api.MirrorService
import cn.junmov.mirror.mine.data.MineRepository
import cn.junmov.mirror.mine.data.local.ProfileDataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MineModule {

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
    ): MineRepository = MineRepository(dataStore,service,database.mineDao())


}