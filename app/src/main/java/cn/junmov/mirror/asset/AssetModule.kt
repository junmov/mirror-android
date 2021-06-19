package cn.junmov.mirror.asset

import cn.junmov.mirror.asset.domain.*
import cn.junmov.mirror.core.data.db.MirrorDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AssetModule {

    @Singleton
    @Provides
    fun createAssetLogUseCase(database: MirrorDatabase) = EntrustOrderUseCase(database.assetDao())

    @Singleton
    @Provides
    fun flowAllLogOfAssetUseCase(database: MirrorDatabase) =
        FlowAllLogOfAssetUseCase(database.assetDao())

    @Singleton
    @Provides
    fun flowAllAssetUseCase(database: MirrorDatabase) = FlowAllAssetUseCase(database.assetDao())

    @Singleton
    @Provides
    fun flowHoldAssetsUseCase(database: MirrorDatabase) = FlowHoldAssetsUseCase(database.assetDao())

    @Singleton
    @Provides
    fun flowAllAssetLogUseCase(database: MirrorDatabase) =
        FlowAllAssetLogUseCase(database.assetDao())

    @Singleton
    @Provides
    fun clinchOrderUseCase(database: MirrorDatabase) = ClinchOrderUseCase(database.assetDao())
}