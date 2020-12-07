package cn.junmov.mirror.core.adapter

fun interface OnListItemClickListener {
    fun click(id: Long, title: String)
}