package cn.junmov.mirror.core.adapter

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import cn.junmov.mirror.R
import cn.junmov.mirror.core.widget.SeparatorViewHolder
import cn.junmov.mirror.core.widget.SingleLineListItemViewHolder

class SingleLinePagingAdapter(
    private val listener: OnNavListener?
) : PagingDataAdapter<SingleLineModel, RecyclerView.ViewHolder>(SingleLineModelComparator) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = when (viewType) {
        R.layout.list_item_single_line -> SingleLineListItemViewHolder.create(parent)
        else -> SeparatorViewHolder.create(parent)
    }

    override fun getItemViewType(position: Int) = when (getItem(position)) {
        is SingleLineModel.UiData -> R.layout.list_item_single_line
        is SingleLineModel.Separator -> R.layout.list_separator
        null -> 0
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position)
        if (holder is SingleLineListItemViewHolder) {
            item?.let {
                holder.bind(item as SingleLineModel.UiData)
                if (listener != null) {
                    holder.onListItemClick { listener.click(item.id, item.title) }
                }
            }
        } else if (holder is SeparatorViewHolder) {
            item?.let { holder.bind(it as SingleLineModel.Separator) }
        }

    }

    companion object {
        private val SingleLineModelComparator = object : DiffUtil.ItemCallback<SingleLineModel>() {
            override fun areItemsTheSame(
                oldItem: SingleLineModel,
                newItem: SingleLineModel
            ): Boolean {
                val isSameRepoItem = oldItem is SingleLineModel.UiData
                        && newItem is SingleLineModel.UiData
                        && oldItem.id == newItem.id
                val isSameSeparatorItem = oldItem is SingleLineModel.Separator
                        && newItem is SingleLineModel.Separator
                        && oldItem.description == newItem.description
                return isSameRepoItem || isSameSeparatorItem
            }

            override fun areContentsTheSame(
                oldItem: SingleLineModel,
                newItem: SingleLineModel
            ): Boolean = oldItem == newItem
        }
    }
}