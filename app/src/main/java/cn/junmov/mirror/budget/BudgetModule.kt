package cn.junmov.mirror.budget

import cn.junmov.mirror.budget.domain.FlowAllFirstBudgetUseCase
import cn.junmov.mirror.budget.domain.FlowAllSecondaryBudgetUseCase
import cn.junmov.mirror.budget.domain.FlowBudgetUseCase
import cn.junmov.mirror.budget.domain.UpdateBudgetUseCase
import cn.junmov.mirror.core.data.MirrorDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
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