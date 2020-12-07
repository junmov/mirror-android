package cn.junmov.mirror.voucher.domain

import cn.junmov.mirror.core.data.dao.VoucherDao
import cn.junmov.mirror.core.data.entity.Voucher
import java.time.LocalDateTime

class SaveVoucherUseCase(private val dao: VoucherDao) {

    suspend operator fun invoke(voucher: Voucher, isCreate: Boolean) {
        if (isCreate) {
            dao.insert(voucher)
        } else {
            voucher.modifiedAt = LocalDateTime.now()
            dao.update(voucher)
        }
    }

}