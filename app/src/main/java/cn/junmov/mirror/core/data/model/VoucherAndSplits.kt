package cn.junmov.mirror.core.data.model

import androidx.room.Embedded
import androidx.room.Relation
import cn.junmov.mirror.core.data.Scheme
import cn.junmov.mirror.core.data.entity.Split
import cn.junmov.mirror.core.data.entity.Voucher

data class VoucherAndSplits(
    @Embedded val voucher: Voucher,

    @Relation(
        parentColumn = Scheme.ID,
        entityColumn = Scheme.Split.VOUCHER_ID
    ) val splits: List<Split> = emptyList()
) {
    fun auditAble(): Boolean {
        var debit = 0
        var credit = 0
        splits.forEach {
            if (it.isDebit) {
                debit += it.amount
            } else {
                credit += it.amount
            }
        }
        return debit != 0 && debit == credit
    }
}