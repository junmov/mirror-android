package cn.junmov.mirror.core.data.model

import androidx.room.Embedded
import cn.junmov.mirror.core.data.entity.Account
import java.time.LocalDate

data class Category(@Embedded val account: Account) {

    val budgetTotal: Int
        get() = account.base + account.inflow

    val budgetUsed: Int
        get() = account.outflow

    val budgetUseAble: Int
        get() = budgetTotal - budgetUsed

    val avgUsed: Int
        get() = budgetUsed.div(LocalDate.now().dayOfMonth)

    val avgUseAble: Int
        get() = budgetUseAble.div(LocalDate.now().lengthOfMonth() - LocalDate.now().dayOfMonth + 1)

    fun use(amount: Int) {
        account.outflow += amount
    }

    fun budgetPlus(amount: Int) {
        account.inflow += amount
    }

    override fun toString(): String = account.toString()

}
