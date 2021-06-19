package cn.junmov.mirror.debt.ui

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import cn.junmov.mirror.core.data.db.entity.Debt
import cn.junmov.mirror.core.widget.FourCellListItemHolder

class DebtListAdapter(
    private val click: (Debt, View) -> Unit
) : ListAdapter<Debt, FourCellListItemHolder>(DIFF_CALL_BACK) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FourCellListItemHolder {
        return FourCellListItemHolder.create(parent)
    }

    override fun onBindViewHolder(holder: FourCellListItemHolder, position: Int) {
        val data = getItem(position)
        holder.bindUiModel(data)
        holder.itemView.setOnClickListener { click(data, it) }
    }

    companion object {
        private val DIFF_CALL_BACK = object : DiffUtil.ItemCallback<Debt>() {
            override fun areItemsTheSame(oldItem: Debt, newItem: Debt): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Debt, newItem: Debt): Boolean {
                return oldItem == newItem
            }
        }
    }
}