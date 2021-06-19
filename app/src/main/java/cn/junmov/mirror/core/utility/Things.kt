package cn.junmov.mirror.core.utility

object Things {
    const val USUAL = "日常"
    const val DEBT = "借贷"
    const val FINANCE = "理财"
    const val HEALTH = "看病"
    const val EARN = "赚钱"
    const val SOCIAL = "人情"

    fun values(): Array<String> = arrayOf(USUAL, DEBT, FINANCE, HEALTH, EARN, SOCIAL)
}