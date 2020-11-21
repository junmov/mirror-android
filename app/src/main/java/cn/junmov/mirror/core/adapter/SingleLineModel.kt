package cn.junmov.mirror.core.adapter

sealed class SingleLineModel {

    data class UiData(
        val id: Long, var primary: String, var action: String,
        var title: String, var separator: String,
    ) : SingleLineModel()

    data class Separator(val description: String) : SingleLineModel()
}