package cn.junmov.mirror.core.utility

import java.time.*
import java.time.format.DateTimeFormatter

object TimeUtils {

    private val tf = DateTimeFormatter.ofPattern("HH:mm:ss")
    private val mf = DateTimeFormatter.ofPattern("yyyy-MM")
    private val df = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    private val dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")

    fun timeToString(time: LocalTime): String = tf.format(time)

    fun stringToTime(str: String): LocalTime = LocalTime.parse(str, tf)

    fun dateToString(date: LocalDate): String = df.format(date)

    fun stringToDate(str: String): LocalDate = LocalDate.parse(str, df)

    fun dateTimeToString(dateTime: LocalDateTime): String = dtf.format(dateTime)

    fun stringToDateTime(str: String): LocalDateTime = LocalDateTime.parse(str, dtf)

    fun longToDateTime(date: Long): LocalDateTime {
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(date), ZoneId.systemDefault())
    }

    fun yearMonthToString(time: YearMonth): String = mf.format(time)

    fun stringToYearMonth(time: String): YearMonth = YearMonth.parse(time, mf)

}