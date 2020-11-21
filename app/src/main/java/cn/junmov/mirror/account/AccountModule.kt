package cn.junmov.mirror.account

import cn.junmov.mirror.account.domain.CreateAccountUseCase
import cn.junmov.mirror.account.domain.FlowAllNoLeafAccountUseCase
import cn.junmov.mirror.core.data.MirrorDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AccountModule {

    @Singleton
    @Provides
    fun createAccountUseCase(database: MirrorDatabase) =
        CreateAccountUseCase(database.accountDao(), database.budgetDao())

    @Singleton
    @Provides
    fun flowAllNoLeafAccountUseCase(database: MirrorDatabase) =
        FlowAllNoLeafAccountUseCase(database.accountDao())
}