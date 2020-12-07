package cn.junmov.mirror.core.adapter

data class ThreeLineData(
    val id: Long,
    var primary: String = "",
    var secondary: String = "",
    var tertiary: String = "",
    var action: String = "",
    var title: String = ""
)
