package cn.junmov.mirror.budget

import cn.junmov.mirror.budget.domain.FlowAllBudgetByParentUseCase
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
    fun flowBudgetUseCase(database: MirrorDatabase) =
        FlowBudgetUseCase(database.budgetDao())

    @Singleton
    @Provides
    fun flowAllBudgetUseCase(database: MirrorDatabase) =
        FlowAllBudgetByParentUseCase(database.budgetDao())

    @Singleton
    @Provides
    fun updateBudgetUseCase(database: MirrorDatabase) =
        UpdateBudgetUseCase(database.budgetDao())

}