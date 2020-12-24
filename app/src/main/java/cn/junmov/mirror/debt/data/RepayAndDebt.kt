package cn.junmov.mirror.debt.data

import androidx.room.Embedded
import androidx.room.Relation
import cn.junmov.mirror.core.data.db.Scheme
import cn.junmov.mirror.core.data.db.entity.Debt
import cn.junmov.mirror.core.data.db.entity.Repay

data class RepayAndDebt(
    @Embedded val repay: Repay,
    @Relation(
        parentColumn = Scheme.Repay.DEBT_ID, entityColumn = Scheme.ID
    )
    val debt: Debt
)
