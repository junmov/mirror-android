package cn.junmov.mirror.core.adapter

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import cn.junmov.mirror.core.widget.ThreeLineListItemViewHolder

class ThreeLinePagingAdapter(
    private val listener: OnListItemClickListener?
) : PagingDataAdapter<ThreeLineData, ThreeLineListItemViewHolder>(DIFF_CALL_BACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ThreeLineListItemViewHolder {
        return ThreeLineListItemViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: ThreeLineListItemViewHolder, position: Int) {
        getItem(position)?.let { data ->
            holder.bind(data)
            if (listener != null) {
                holder.onListItemClick {
                    listener.click(data.id, data.title)
                }
            }
        }
    }


    companion object {
        private val DIFF_CALL_BACK = object : DiffUtil.ItemCallback<ThreeLineData>() {
            override fun areItemsTheSame(oldItem: ThreeLineData, newItem: ThreeLineData): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: ThreeLineData,
                newItem: ThreeLineData
            ): Boolean {
                return oldItem == newItem
            }

        }
    }
}