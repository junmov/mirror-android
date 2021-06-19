package cn.junmov.mirror.debt.ui

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import cn.junmov.mirror.R
import cn.junmov.mirror.core.data.db.entity.Repay
import cn.junmov.mirror.core.utility.MoneyUtils
import cn.junmov.mirror.core.utility.TimeUtils
import cn.junmov.mirror.core.utility.setString
import cn.junmov.mirror.core.widget.TwoLineListItemViewHolder

class RepayListAdapter(
    private val itemClick: (View, Repay) -> Any
) : ListAdapter<Repay, TwoLineListItemViewHolder>(DIFF_CALL_BACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TwoLineListItemViewHolder {
        return TwoLineListItemViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: TwoLineListItemViewHolder, position: Int) {
        val data = getItem(position)
        with(holder) {
            primaryText.text = TimeUtils.dateToString(data.dateAt)
            secondaryText.setString(
                R.string.repay_secondary,
                MoneyUtils.centToYuan(data.capital + data.interest),
                MoneyUtils.centToYuan(data.capital),
                MoneyUtils.centToYuan(data.interest)
            )
            actionText.setString(
                if (data.repaid) R.string.repay_settled
                else R.string.repay_no_settled
            )
            itemView.setOnClickListener { itemClick(it, data) }
        }
    }

    companion object {
        private val DIFF_CALL_BACK = object : DiffUtil.ItemCallback<Repay>() {
            override fun areItemsTheSame(
                oldItem: Repay, newItem: Repay
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: Repay, newItem: Repay
            ): Boolean {
                return oldItem == newItem
            }
        }
    }


}
