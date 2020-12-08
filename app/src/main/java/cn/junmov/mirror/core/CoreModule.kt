package cn.junmov.mirror.core

import android.content.Context
import androidx.room.Room
import cn.junmov.mirror.core.data.MirrorDatabase
import cn.junmov.mirror.core.data.Scheme
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
        return Room.databaseBuilder(
            context.applicationContext, MirrorDatabase::class.java, Scheme.DATABASE_NAME
        )
            .createFromAsset("database/mirror.db")
            .build()
    }

}