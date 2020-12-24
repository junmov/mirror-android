package cn.junmov.mirror.debt.ui

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import cn.junmov.mirror.R
import cn.junmov.mirror.core.data.db.entity.Repay
import cn.junmov.mirror.core.utility.MoneyUtils
import cn.junmov.mirror.core.utility.TimeUtils
import cn.junmov.mirror.core.utility.navTo
import cn.junmov.mirror.core.utility.setString
import cn.junmov.mirror.core.widget.ThreeLineListItemViewHolder

class RepayListAdapter : ListAdapter<Repay, ThreeLineListItemViewHolder>(DIFF_CALL_BACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ThreeLineListItemViewHolder {
        return ThreeLineListItemViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: ThreeLineListItemViewHolder, position: Int) {
        val data = getItem(position)
        with(holder) {
            primary.text = data.summary
            secondary.setString(
                R.string.repay_secondary,
                MoneyUtils.centToYuan(data.capital+ data.interest),
                MoneyUtils.centToYuan(data.capital),
                MoneyUtils.centToYuan(data.interest)
            )
            tertiary.setString(
                R.string.repay_tertiary,
                TimeUtils.dateToString(data.dateAt)
            )
            action.setString(
                if (data.settled) R.string.repay_settled
                else R.string.repay_no_settled
            )
            if (!data.settled) {
                holder.itemView.navTo(
                    DebtDetailFragmentDirections.actionDebtDetailFragmentToRepayFormDialog(data.id)
                )
            }
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
