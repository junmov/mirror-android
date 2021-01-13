package cn.junmov.mirror.debt.data

import androidx.room.ColumnInfo
import cn.junmov.mirror.core.data.db.Scheme
import java.time.LocalDate

data class DateRepay(
    @ColumnInfo(name = Scheme.Repay.DATE_AT) val dateAt: LocalDate,
    @ColumnInfo(name = Scheme.Repay.CAPITAL) val capital: Int,
    @ColumnInfo(name = Scheme.Repay.INTEREST) val interest: Int
) {
    fun amount(): Int = capital + interest
}