package cn.junmov.mirror.debt.data

import androidx.room.Embedded
import androidx.room.Relation
import cn.junmov.mirror.core.data.Scheme
import cn.junmov.mirror.core.data.entity.Debt
import cn.junmov.mirror.core.data.entity.Repay

data class RepayAndDebt(
    @Embedded val repay: Repay,
    @Relation(
        parentColumn = Scheme.Repay.DEBT_ID, entityColumn = Scheme.ID
    )
    val debt: Debt
)
