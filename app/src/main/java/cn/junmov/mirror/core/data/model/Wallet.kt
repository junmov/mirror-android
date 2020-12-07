package cn.junmov.mirror.core.data.model

import androidx.room.Embedded
import cn.junmov.mirror.core.data.entity.Account

data class Wallet(@Embedded val account: Account) {

    fun balance(): Int = account.base

    fun collect(amount: Int) {
        account.inflow += amount
    }

    fun pay(amount: Int) {
        account.outflow += amount
    }

}