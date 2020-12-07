package cn.junmov.mirror.voucher.ui

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import cn.junmov.mirror.MainNavDirections
import cn.junmov.mirror.core.utility.MoneyUtils
import cn.junmov.mirror.core.utility.TimeUtils
import cn.junmov.mirror.core.utility.navTo
import cn.junmov.mirror.core.widget.TwoLineListItemViewHolder
import cn.junmov.mirror.voucher.data.ItemVoucher

class VoucherListAdapter : ListAdapter<ItemVoucher, TwoLineListItemViewHolder>(DIFF_CALL_BACK) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TwoLineListItemViewHolder {
        return TwoLineListItemViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: TwoLineListItemViewHolder, position: Int) {
        val data = getItem(position)
        with(holder) {
            bind(
                data.summary,
                "${data.thing} ${TimeUtils.dateToString(data.dateAt)}",
                MoneyUtils.centToYuan(data.profit)
            )
            itemView.navTo(MainNavDirections.actionGlobalVoucherDetailFragment(data.id))
        }
    }

    companion object {
        private val DIFF_CALL_BACK = object : DiffUtil.ItemCallback<ItemVoucher>() {
            override fun areItemsTheSame(oldItem: ItemVoucher, newItem: ItemVoucher): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: ItemVoucher, newItem: ItemVoucher): Boolean {
                return oldItem == newItem
            }

        }
    }
}