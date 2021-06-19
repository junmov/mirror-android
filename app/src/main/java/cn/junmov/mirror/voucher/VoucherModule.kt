package cn.junmov.mirror.voucher

import cn.junmov.mirror.core.data.db.MirrorDatabase
import cn.junmov.mirror.voucher.domain.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object VoucherModule {

    @Singleton
    @Provides
    fun flowAllAccountTradeCountDescUseCase(database: MirrorDatabase) =
        FlowAllAccountTradeCountDescUseCase(database.accountDao())

    @Singleton
    @Provides
    fun flowAccountTradeLimitUseCase(database: MirrorDatabase) =
        FlowAccountTradeLimitUseCase(database.voucherDao())

    @Singleton
    @Provides
    fun flowLastThreeVoucherUseCase(database: MirrorDatabase) =
        FlowLastThreeVoucherUseCase(database.voucherDao())

    @Singleton
    @Provides
    fun copyVoucherUseCase(database: MirrorDatabase) =
        CopyVoucherUseCase(database.voucherDao(), database.accountDao())

    @Singleton
    @Provides
    fun removeVoucherUseCase(database: MirrorDatabase) =
        RemoveVoucherUseCase(database.voucherDao(), database.accountDao())

    @Singleton
    @Provides
    fun saveVoucherUseCase(database: MirrorDatabase) =
        CreateVoucherUseCase(database.voucherDao(), database.accountDao())


    @Singleton
    @Provides
    fun pagingAccountTradingUseCase(database: MirrorDatabase) =
        PagingVoucherInAccountUseCase(database.voucherDao())
}