package cn.junmov.mirror.account.data

import androidx.recyclerview.widget.DiffUtil
import cn.junmov.mirror.core.data.entity.Account

object AccountDiffCallBack : DiffUtil.ItemCallback<Account>() {
    override fun areItemsTheSame(oldItem: Account, newItem: Account): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Account, newItem: Account): Boolean {
        return oldItem == newItem
    }
}