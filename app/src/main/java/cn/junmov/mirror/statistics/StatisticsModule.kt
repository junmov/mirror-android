package cn.junmov.mirror.statistics

import cn.junmov.mirror.core.data.db.MirrorDatabase
import cn.junmov.mirror.statistics.domain.PagingVoucherInAccountUseCase
import cn.junmov.mirror.statistics.domain.PagingScheduledVoucherUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object StatisticsModule {

    @Singleton
    @Provides
    fun pagingAccountTradingUseCase(database: MirrorDatabase) =
        PagingVoucherInAccountUseCase(database.accountDao())

    @Singleton
    @Provides
    fun pagingScheduledVoucherUseCase(database: MirrorDatabase) =
        PagingScheduledVoucherUseCase(database.voucherDao())

}