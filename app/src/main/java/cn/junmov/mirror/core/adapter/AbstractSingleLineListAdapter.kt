package cn.junmov.mirror.core.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import cn.junmov.mirror.core.widget.SingleLineListItemViewHolder

abstract class AbstractSingleLineListAdapter<T>(diff: DiffUtil.ItemCallback<T>) :
    ListAdapter<T, SingleLineListItemViewHolder>(diff) {

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): SingleLineListItemViewHolder {
        return SingleLineListItemViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: SingleLineListItemViewHolder, position: Int) {
        val data = getItem(position)
        holder.bind(trans(data))
        click(data)?.let { holder.onListItemClick(it) }
    }

    abstract fun trans(data: T): SingleLineModel.UiData

    open fun click(data: T): View.OnClickListener? = null
}