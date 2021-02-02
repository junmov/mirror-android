package cn.junmov.mirror.budget.data

import androidx.room.ColumnInfo
import cn.junmov.mirror.core.data.db.Scheme
import cn.junmov.mirror.core.data.model.AccountType

data class Budget(
    @ColumnInfo(name = Scheme.ID) val id: Long,
    @ColumnInfo(name = Scheme.Account.NAME) val name: String,
    @ColumnInfo(name = Scheme.Account.TYPE) val type: AccountType,
    @ColumnInfo(name = Scheme.Account.BASE) val base: Int,
    @ColumnInfo(name = Scheme.Account.INFLOW) val debit: Int,
    @ColumnInfo(name = Scheme.Account.OUTFLOW) val credit: Int
) {

    fun total(): Int = base

    fun used(): Int = when {
        type.isExpend() -> debit - credit
        type.isIncome() -> credit - debit
        else -> 0
    }

    fun useAble(): Int = total() - used()

    override fun toString(): String = name

}