package cn.junmov.mirror.statistics

import cn.junmov.mirror.core.data.MirrorDatabase
import cn.junmov.mirror.statistics.domain.PagingAccountTradingUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object StatisticsModule {

    @Singleton
    @Provides
    fun pagingAccountTradingUseCase(database: MirrorDatabase) =
        PagingAccountTradingUseCase(database.statisticsDao())

}