package cn.junmov.mirror.voucher.data

import androidx.recyclerview.widget.DiffUtil
import cn.junmov.mirror.core.data.db.view.ItemVoucher

object ItemVoucherDiffCallBack : DiffUtil.ItemCallback<ItemVoucher>() {
    override fun areItemsTheSame(oldItem: ItemVoucher, newItem: ItemVoucher): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: ItemVoucher, newItem: ItemVoucher): Boolean {
        return oldItem == newItem
    }
}