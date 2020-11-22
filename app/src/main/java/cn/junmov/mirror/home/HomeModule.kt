package cn.junmov.mirror.home

import cn.junmov.mirror.core.data.MirrorDatabase
import cn.junmov.mirror.home.domain.PagingVoucherUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object HomeModule {

    @Singleton
    @Provides
    fun pagingVoucherUseCase(database: MirrorDatabase) =
        PagingVoucherUseCase(database.voucherDao())

}