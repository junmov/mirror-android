package cn.junmov.mirror.debt

import cn.junmov.mirror.core.data.MirrorDatabase
import cn.junmov.mirror.debt.domain.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object DebtModule {
    @Singleton
    @Provides
    fun createDebtUseCase(database: MirrorDatabase) =
        CreateDebtUseCase(database.debtDao(), database.billDao())

    @Singleton
    @Provides
    fun flowAllDebtAccountUseCase(database: MirrorDatabase) =
        FlowAllDebtAccountUseCase(database.accountDao())

    @Singleton
    @Provides
    fun flowAllDebtUseCase(database: MirrorDatabase) =
        FlowAllDebtUseCase(database.debtDao())

    @Singleton
    @Provides
    fun flowDebtInfoUseCase(database: MirrorDatabase) =
        FlowDebtInfoUseCase(database.debtDao())

    @Singleton
    @Provides
    fun flowDebtUseCase(database: MirrorDatabase) =
        FlowDebtUseCase(database.debtDao())

    @Singleton
    @Provides
    fun flowRepayUseCase(database: MirrorDatabase) =
        FlowRepayUseCase(database.debtDao())

    @Singleton
    @Provides
    fun pagingBillBySettledUseCase(database: MirrorDatabase) =
        PagingBillBySettledUseCase(database.billDao())

    @Singleton
    @Provides
    fun stopLossUseCase(database: MirrorDatabase) =
        StopLossUseCase(database.debtDao(), database.billDao())

    @Singleton
    @Provides
    fun updateRepayUseCase(database: MirrorDatabase) =
        UpdateRepayUseCase(database.debtDao(), database.billDao())

}