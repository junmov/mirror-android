package cn.junmov.mirror.core.data.model

import androidx.room.Embedded
import androidx.room.Relation
import cn.junmov.mirror.core.data.db.Scheme
import cn.junmov.mirror.core.data.db.entity.Debt
import cn.junmov.mirror.core.data.db.entity.Repay

data class DebtInfo(

    @Embedded val debt: Debt,

    @Relation(
        parentColumn = Scheme.ID,
        entityColumn = Scheme.Repay.DEBT_ID
    )
    val items: List<Repay>

)