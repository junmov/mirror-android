package cn.junmov.mirror.core.data.model

import androidx.recyclerview.widget.DiffUtil
import cn.junmov.mirror.core.data.db.entity.Account

object AccountDiffCallBack : DiffUtil.ItemCallback<Account>() {
    override fun areItemsTheSame(oldItem: Account, newItem: Account): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Account, newItem: Account): Boolean {
        return oldItem == newItem
    }
}