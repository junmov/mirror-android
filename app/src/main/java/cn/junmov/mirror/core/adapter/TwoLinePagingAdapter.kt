package cn.junmov.mirror.core.adapter

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import cn.junmov.mirror.R
import cn.junmov.mirror.core.widget.SeparatorViewHolder
import cn.junmov.mirror.core.widget.TwoLineListItemViewHolder

open class TwoLinePagingAdapter(
    private val listener: OnNavListener?
) : PagingDataAdapter<TwoLineModel, RecyclerView.ViewHolder>(TwoLineItemComparator) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = when (viewType) {
        R.layout.list_item_two_line -> TwoLineListItemViewHolder.create(parent)
        else -> SeparatorViewHolder.create(parent)
    }

    override fun getItemViewType(position: Int) = when (getItem(position)) {
        is TwoLineModel.UiData -> R.layout.list_item_two_line
        is TwoLineModel.Separator -> R.layout.list_separator
        null -> 0
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position)
        if (holder is TwoLineListItemViewHolder) {
            item?.let {
                holder.bind(it as TwoLineModel.UiData)
                if (listener != null) {
                    holder.itemView.setOnClickListener { _ -> listener.click(it.id, it.title) }
                }
            }
        } else if (holder is SeparatorViewHolder) {
            item?.let { holder.bind(it as TwoLineModel.Separator) }
        }
    }

    companion object {
        private val TwoLineItemComparator = object : DiffUtil.ItemCallback<TwoLineModel>() {
            override fun areItemsTheSame(
                oldItem: TwoLineModel, newItem: TwoLineModel
            ): Boolean {
                val isSameData = oldItem is TwoLineModel.UiData
                        && newItem is TwoLineModel.UiData
                        && oldItem.id == newItem.id
                val isSameSeparator = oldItem is TwoLineModel.Separator
                        && newItem is TwoLineModel.Separator
                        && oldItem.description == newItem.description
                return isSameData || isSameSeparator
            }

            override fun areContentsTheSame(
                oldItem: TwoLineModel, newItem: TwoLineModel
            ): Boolean {
                return oldItem == newItem
            }

        }
    }
}