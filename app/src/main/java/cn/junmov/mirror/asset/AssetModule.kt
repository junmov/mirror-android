package cn.junmov.mirror.asset

import cn.junmov.mirror.asset.domain.*
import cn.junmov.mirror.core.data.MirrorDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AssetModule {

    @Singleton
    @Provides
    fun createAssetLogUseCase(database: MirrorDatabase) =
        CreateAssetLogUseCase(database.assetDao())

    @Singleton
    @Provides
    fun createAssetUseCase(database: MirrorDatabase) =
        CreateAssetUseCase(database.assetDao())

    @Singleton
    @Provides
    fun flowAllAssetLogUseCase(database: MirrorDatabase) =
        FlowAllAssetLogUseCase(database.assetDao())

    @Singleton
    @Provides
    fun flowAllAssetUseCase(database: MirrorDatabase) =
        FlowAllAssetUseCase(database.assetDao())

    @Singleton
    @Provides
    fun flowAssetUseCase(database: MirrorDatabase) =
        FlowAssetUseCase(database.assetDao())

}