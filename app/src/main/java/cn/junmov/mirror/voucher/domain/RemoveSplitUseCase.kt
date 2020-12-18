package cn.junmov.mirror.voucher.domain

import cn.junmov.mirror.core.data.dao.VoucherDao
import cn.junmov.mirror.core.data.entity.Split
import java.time.LocalDateTime

class RemoveSplitUseCase(private val dao: VoucherDao) {

    suspend operator fun invoke(split: Split) {
        split.deleted = true
        split.modifiedAt = LocalDateTime.now()
        dao.updateSplit(split)
    }

}