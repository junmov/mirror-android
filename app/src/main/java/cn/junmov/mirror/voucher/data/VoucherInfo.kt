package cn.junmov.mirror.voucher.data

import androidx.room.Embedded
import androidx.room.Relation
import cn.junmov.mirror.core.data.Scheme
import cn.junmov.mirror.core.data.entity.Split
import cn.junmov.mirror.core.data.entity.Voucher

data class VoucherInfo(
    @Embedded val voucher: Voucher,

    @Relation(
        parentColumn = Scheme.ID,
        entityColumn = Scheme.Split.VOUCHER_ID
    ) val splits: List<Split> = emptyList()
)