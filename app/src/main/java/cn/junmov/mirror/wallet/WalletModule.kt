package cn.junmov.mirror.wallet

import cn.junmov.mirror.core.data.MirrorDatabase
import cn.junmov.mirror.wallet.domain.CreateAccountUseCase
import cn.junmov.mirror.wallet.domain.FlowAllWalletUseCase
import cn.junmov.mirror.wallet.domain.FlowWalletTradeLimitUseCase
import cn.junmov.mirror.wallet.domain.FlowWalletUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
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


}