package cn.junmov.mirror.core.data.model

import cn.junmov.mirror.core.data.db.entity.Split
import cn.junmov.mirror.core.data.db.entity.Voucher

data class VoucherAndSplit(
    val voucher: Voucher,
    val splits: List<Split>
)
