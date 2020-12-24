package cn.junmov.mirror.core

import android.content.Context
import cn.junmov.mirror.core.data.db.MirrorDatabase
import cn.junmov.mirror.core.data.remote.MirrorService
import cn.junmov.mirror.core.data.store.ProfileDataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CoreModule {

    @Singleton
    @Provides
    fun provideDataBase(@ApplicationContext context: Context): MirrorDatabase {
        return MirrorDatabase.getInstance(context)
    }

    @Provides
    @Singleton
    fun mirrorService(): MirrorService = MirrorService.create()

    @Provides
    @Singleton
    fun profileDataStore(@ApplicationContext ctx: Context): ProfileDataStore = ProfileDataStore(ctx)

}