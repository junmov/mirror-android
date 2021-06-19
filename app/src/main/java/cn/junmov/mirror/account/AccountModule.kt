package cn.junmov.mirror.account

import cn.junmov.mirror.account.domain.*
import cn.junmov.mirror.core.data.db.MirrorDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AccountModule {

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
    fun updateWalletBalanceUseCase(database: MirrorDatabase) =
        UpdateWalletBalanceUseCase(database.accountDao())

    @Singleton
    @Provides
    fun flowAllFirstBudgetUseCase(database: MirrorDatabase) =
        FlowAllBudgetUseCase(database.accountDao())

    @Singleton
    @Provides
    fun flowAccountUseCase(database: MirrorDatabase) =
        FlowAccountUseCase(database.accountDao())

    @Singleton
    @Provides
    fun updateBudgetUseCase(database: MirrorDatabase) =
        AdjustBudgetUseCase(database.accountDao())

    @Singleton
    @Provides
    fun flowAllAccount(database: MirrorDatabase) = FlowAllAccountUseCase(database.accountDao())

    @Singleton
    @Provides
    fun flowAllAccountByTypeUseCase(database: MirrorDatabase) =
        FlowAllAccountByTypeUseCase(database.accountDao())

    @Singleton
    @Provides
    fun budgetMonthlyUseCase(database: MirrorDatabase) = BudgetMonthlyUseCase(database.accountDao())
}