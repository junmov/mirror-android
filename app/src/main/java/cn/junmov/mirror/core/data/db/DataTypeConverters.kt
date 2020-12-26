package cn.junmov.mirror.core.data.db

import androidx.room.TypeConverter
import cn.junmov.mirror.core.data.model.AccountType
import cn.junmov.mirror.core.data.model.PeriodType
import cn.junmov.mirror.core.utility.TimeUtils
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

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
    fun accountTypeToStr(type: AccountType): String = type.name

    @TypeConverter
    fun strToAccountType(type: String): AccountType = AccountType.valueOf(type)

    @TypeConverter
    fun periodTypeToStr(type: PeriodType): String = type.name

    @TypeConverter
    fun strToPeriodType(type: String): PeriodType = PeriodType.valueOf(type)

}