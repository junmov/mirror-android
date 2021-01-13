package cn.junmov.mirror.core.data.db.entity

import cn.junmov.mirror.core.data.model.PeriodType
import java.time.LocalDate

/**
 * 已计划的提醒
 */
interface ToDoEntity : CommonField {
    /** 提示摘要 */
    var summary: String

    /** 是否开启 */
    var enabled: Boolean

    /** 下次运行时间 */
    var runAt: LocalDate

    /** 循环周期 */
    var period: PeriodType

    /** 完成的次数 */
    var doneTimes: Int

    /** 需要完成的次数 */
    var doneTotal: Int
}