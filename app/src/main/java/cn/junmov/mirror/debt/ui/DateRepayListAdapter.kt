package cn.junmov.mirror.debt.ui

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import cn.junmov.mirror.core.utility.MoneyUtils
import cn.junmov.mirror.core.utility.TimeUtils
import cn.junmov.mirror.core.utility.navTo
import cn.junmov.mirror.core.widget.TwoLineListItemViewHolder
import cn.junmov.mirror.debt.data.DateRepay

class DateRepayListAdapter : ListAdapter<DateRepay, TwoLineListItemViewHolder>(diff_call_back) {

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): TwoLineListItemViewHolder {
        return TwoLineListItemViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: TwoLineListItemViewHolder, position: Int) {
        val data = getItem(position)
        val first = TimeUtils.dateToString(data.dateAt)
        val second =
            "本金：${MoneyUtils.centToYuan(data.capital)} 利息：${MoneyUtils.centToYuan(data.interest)}"
        val third = MoneyUtils.centToYuan(data.amount())
        with(holder) {
            bind(first, second, third)
            itemView.navTo(
                BillTabFragmentDirections.actionBillTabFragmentToRepayDetailFragment(
                    TimeUtils.dateToString(data.dateAt)
                )
            )
        }
    }

    companion object {
        private val diff_call_back = object : DiffUtil.ItemCallback<DateRepay>() {
            override fun areItemsTheSame(oldItem: DateRepay, newItem: DateRepay): Boolean {
                return oldItem.dateAt == newItem.dateAt
            }

            override fun areContentsTheSame(oldItem: DateRepay, newItem: DateRepay): Boolean {
                return oldItem == newItem
            }
        }
    }
}