package cn.junmov.mirror.core.data

import androidx.room.TypeConverter
import cn.junmov.mirror.core.utility.TimeUtils
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.YearMonth

class DataTypeConverters {

    @TypeConverter
    fun timeToStr(time: LocalTime): String = TimeUtils.timeToString(time)

    @TypeConverter
    fun strToTime(time: String): LocalTime = TimeUtils.stringToTime(time)

    @TypeConverter
    fun dateToStr(time: LocalDate): String = TimeUtils.dateToString(time)

    @TypeConverter
    fun strToDate(time: String): LocalDate = TimeUtils.stringToDate(time)

    @TypeConverter
    fun dateTimeToStr(time: LocalDateTime): String = TimeUtils.dateTimeToString(time)

    @TypeConverter
    fun strToDateTime(time: String): LocalDateTime = TimeUtils.stringToDateTime(time)

    @TypeConverter
    fun yearMonthToStr(time: YearMonth): String = TimeUtils.yearMonthToString(time)

    @TypeConverter
    fun strToYearMonth(time: String): YearMonth = TimeUtils.stringToYearMonth(time)

    @TypeConverter
    fun accountTypeToString(type: AccountType): String = type.name

    @TypeConverter
    fun stringToAccountType(type: String): AccountType = AccountType.valueOf(type)

}