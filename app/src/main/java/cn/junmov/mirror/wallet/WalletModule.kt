package cn.junmov.mirror.wallet

import cn.junmov.mirror.core.data.db.MirrorDatabase
import cn.junmov.mirror.wallet.domain.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object WalletModule {

    @Singleton
    @Provides
    fun createAccountUseCase(database: MirrorDatabase) =
        CreateAccountUseCase(database.accountDao())

    @Singleton
    @Provides
    fun flowAllWalletUseCase(database: MirrorDatabase) =
        FlowAllWalletUseCase(database.accountDao())

    @Singleton
    @Provides
    fun flowWalletTradeLimitUseCase(database: MirrorDatabase) =
        FlowWalletTradeLimitUseCase(database.tradeDao())

    @Singleton
    @Provides
    fun flowWalletUseCase(database: MirrorDatabase) =
        FlowWalletUseCase(database.accountDao())

    @Singleton
    @Provides
    fun updateWalletBalanceUseCase(database: MirrorDatabase) =
        UpdateWalletBalanceUseCase(database.accountDao())

}