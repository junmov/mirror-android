package cn.junmov.mirror.core.adapter

sealed class TwoLineModel {
    data class UiData(
        val id: Long, var primary: String, var secondary: String, var action: String,
        var title: String, val separator: String,
    ) : TwoLineModel()

    class Separator(val description: String) : TwoLineModel()
}