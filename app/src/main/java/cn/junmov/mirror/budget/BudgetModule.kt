package cn.junmov.mirror.budget

import cn.junmov.mirror.budget.domain.FlowAllFirstBudgetUseCase
import cn.junmov.mirror.budget.domain.FlowAllSecondaryBudgetUseCase
import cn.junmov.mirror.budget.domain.FlowBudgetUseCase
import cn.junmov.mirror.budget.domain.UpdateBudgetUseCase
import cn.junmov.mirror.core.data.db.MirrorDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object BudgetModule {

    @Singleton
    @Provides
    fun flowAllFirstBudgetUseCase(database: MirrorDatabase) =
        FlowAllFirstBudgetUseCase(database.accountDao())

    @Singleton
    @Provides
    fun flowAllSecondaryBudgetUseCase(database: MirrorDatabase) =
        FlowAllSecondaryBudgetUseCase(database.accountDao())

    @Singleton
    @Provides
    fun flowBudgetUseCase(database: MirrorDatabase) =
        FlowBudgetUseCase(database.accountDao())

    @Singleton
    @Provides
    fun updateBudgetUseCase(database: MirrorDatabase) =
        UpdateBudgetUseCase(database.accountDao())

}