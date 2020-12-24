package cn.junmov.mirror.home

import cn.junmov.mirror.core.data.db.MirrorDatabase
import cn.junmov.mirror.home.domain.FlowAllTodayDoneTodoUseCase
import cn.junmov.mirror.home.domain.FlowLastThreeVoucherUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HomeModule {

    @Singleton
    @Provides
    fun flowAllTodayDoneTodoUseCase(database: MirrorDatabase) =
        FlowAllTodayDoneTodoUseCase(database.toDoDao())

    @Singleton
    @Provides
    fun flowLastThreeVoucherUseCase(database: MirrorDatabase) =
        FlowLastThreeVoucherUseCase(database.voucherDao())

}