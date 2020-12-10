package cn.junmov.mirror.core

import android.content.Context
import cn.junmov.mirror.core.data.MirrorDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object CoreModule {

    @Singleton
    @Provides
    fun provideDataBase(@ApplicationContext context: Context): MirrorDatabase {
        return MirrorDatabase.getInstance(context)
    }

}