package cn.junmov.mirror.voucher

import cn.junmov.mirror.core.data.MirrorDatabase
import cn.junmov.mirror.voucher.domain.AuditVoucherUseCase
import cn.junmov.mirror.voucher.domain.CopyVoucherUseCase
import cn.junmov.mirror.voucher.domain.FlowVoucherInfoUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object VoucherModule {

    @Singleton
    @Provides
    fun auditVoucherUseCase(database: MirrorDatabase) =
        AuditVoucherUseCase(database.auditDao(), database.accountDao(), database.budgetDao())

    @Singleton
    @Provides
    fun copyVoucherUseCase(auditVoucherUseCase: AuditVoucherUseCase) =
        CopyVoucherUseCase(auditVoucherUseCase)

    @Singleton
    @Provides
    fun flowVoucherInfoUseCase(database: MirrorDatabase) =
        FlowVoucherInfoUseCase(database.voucherDao())

}