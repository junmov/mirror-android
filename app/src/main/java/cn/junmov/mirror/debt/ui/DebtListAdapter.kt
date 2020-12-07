package cn.junmov.mirror.debt.ui

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import cn.junmov.mirror.core.data.entity.Debt
import cn.junmov.mirror.core.utility.navTo
import cn.junmov.mirror.core.widget.TwoLineListItemViewHolder

class DebtListAdapter : ListAdapter<Debt, TwoLineListItemViewHolder>(DIFF_CALL_BACK) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TwoLineListItemViewHolder {
        return TwoLineListItemViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: TwoLineListItemViewHolder, position: Int) {
        val data = getItem(position)
        with(holder) {
            bind(data.twoLineData())
            itemView.navTo(
                BillTabFragmentDirections.actionBillTabFragmentToDebtDetailFragment(data.id)
            )
        }
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