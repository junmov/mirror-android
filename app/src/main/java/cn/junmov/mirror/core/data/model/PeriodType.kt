package cn.junmov.mirror.core.data.model

import java.time.LocalDate

enum class PeriodType(private val typeName: String) {
    /** 每天 */
    DAILY("每天"),

    /** 每周 */
    WEEKLY("每7天"),

    /** 每月 */
    MONTHLY("每月的同一天"),

    /** 每年 */
    YEARLY("每年的同一天");

    override fun toString(): String = typeName

    fun nextPeriod(lastPeriod: LocalDate): LocalDate {
        return when (this) {
            DAILY -> lastPeriod.plusDays(1)
            WEEKLY -> lastPeriod.plusDays(7)
            MONTHLY -> lastPeriod.plusMonths(1)
            YEARLY -> lastPeriod.plusYears(1)
        }
    }
}