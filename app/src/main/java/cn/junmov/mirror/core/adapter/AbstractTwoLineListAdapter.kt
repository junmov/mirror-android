package cn.junmov.mirror.core.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import cn.junmov.mirror.core.widget.TwoLineListItemViewHolder

abstract class AbstractTwoLineListAdapter<T>(private val diff: DiffUtil.ItemCallback<T>) :
    ListAdapter<T, TwoLineListItemViewHolder>(diff) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TwoLineListItemViewHolder {
        return TwoLineListItemViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: TwoLineListItemViewHolder, position: Int) {
        val data = getItem(position)
        holder.bind(trans(data))
        click(data)?.let { holder.onListItemClick(it) }
    }

    abstract fun trans(data: T): TwoLineModel.UiData

    open fun click(data: T): View.OnClickListener? = null
}