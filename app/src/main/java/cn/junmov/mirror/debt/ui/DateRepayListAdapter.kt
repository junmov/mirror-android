package cn.junmov.mirror.debt.ui

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import cn.junmov.mirror.core.utility.MoneyUtils
import cn.junmov.mirror.core.utility.TimeUtils
import cn.junmov.mirror.core.widget.TwoLineListItemViewHolder
import cn.junmov.mirror.debt.data.DateRepay

class DateRepayListAdapter(
    private val click: (DateRepay, View) -> Unit
) : ListAdapter<DateRepay, TwoLineListItemViewHolder>(diff_call_back) {

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): TwoLineListItemViewHolder {
        return TwoLineListItemViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: TwoLineListItemViewHolder, position: Int) {
        val data = getItem(position)
        with(holder) {
            bind(
                primary = TimeUtils.dateToString(data.dateAt),
                secondary =
                "本金：${MoneyUtils.centToYuan(data.capital)} 利息：${MoneyUtils.centToYuan(data.interest)}",
                top = MoneyUtils.centToYuan(data.amount())
            )
            itemView.setOnClickListener { click(data, it) }
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