package cn.junmov.mirror.core.data.model

import androidx.room.Embedded
import androidx.room.Relation
import cn.junmov.mirror.core.data.Scheme
import cn.junmov.mirror.core.data.entity.Debt
import cn.junmov.mirror.core.data.entity.Repay

data class DebtInfo(

    @Embedded val debt: Debt,

    @Relation(
        parentColumn = Scheme.ID,
        entityColumn = Scheme.Repay.DEBT_ID
    )
    val items: List<Repay>

)