package cn.junmov.mirror.account.data

import androidx.room.Embedded
import androidx.room.Relation
import cn.junmov.mirror.core.data.Scheme
import cn.junmov.mirror.core.data.entity.Account

data class AccountWithChildren(
    @Embedded val parent: Account,

    @Relation(
        parentColumn = Scheme.ID,
        entityColumn = Scheme.Account.PARENT_ID
    )
    val children: List<Account> = emptyList()
)
