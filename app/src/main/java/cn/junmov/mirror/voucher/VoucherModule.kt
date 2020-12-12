package cn.junmov.mirror.voucher

import cn.junmov.mirror.core.data.MirrorDatabase
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
    fun auditVoucherUseCase(database: MirrorDatabase) =
        AuditVoucherUseCase(database.auditDao(), database.accountDao())

    @Singleton
    @Provides
    fun copyVoucherUseCase(database: MirrorDatabase) =
        CopyVoucherUseCase(database.voucherDao())

    @Singleton
    @Provides
    fun flowAllSplitByVoucher(database: MirrorDatabase) =
        FlowAllSplitByVoucherUseCase(database.voucherDao())

    @Singleton
    @Provides
    fun flowAllAccountByLeafUseCase(database: MirrorDatabase) =
        FlowAllTradAbleAccountUseCase(database.accountDao())

    @Singleton
    @Provides
    fun flowVoucherUseCase(database: MirrorDatabase) =
        FlowVoucherUseCase(database.voucherDao())

    @Singleton
    @Provides
    fun removeSplitUseCase(database: MirrorDatabase) =
        RemoveSplitUseCase(database.voucherDao())

    @Singleton
    @Provides
    fun saveSplitUseCase(database: MirrorDatabase) =
        SaveSplitUseCase(database.voucherDao())

    @Singleton
    @Provides
    fun saveVoucherUseCase(database: MirrorDatabase) =
        SaveVoucherUseCase(database.voucherDao())


}