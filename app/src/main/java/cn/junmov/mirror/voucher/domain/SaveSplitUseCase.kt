package cn.junmov.mirror.voucher.domain

import cn.junmov.mirror.core.data.dao.VoucherDao
import cn.junmov.mirror.core.data.entity.Split
import java.time.LocalDateTime

class SaveSplitUseCase(private val dao: VoucherDao) {
    suspend operator fun invoke(split: Split, isCreate: Boolean) {
        if (isCreate) {
            dao.insertSplit(split)
        } else {
            split.modifiedAt = LocalDateTime.now()
            dao.updateSplit(split)
        }
    }
}