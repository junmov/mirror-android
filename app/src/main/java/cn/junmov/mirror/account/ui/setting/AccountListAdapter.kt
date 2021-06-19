package cn.junmov.mirror.account.ui.setting

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import cn.junmov.mirror.account.data.AccountDiffCallBack
import cn.junmov.mirror.core.data.db.entity.Account
import cn.junmov.mirror.core.widget.SingleLineListItemViewHolder

class AccountListAdapter(
    private val click: (View, Account) -> Unit
) : ListAdapter<Account, SingleLineListItemViewHolder>(AccountDiffCallBack) {
    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): SingleLineListItemViewHolder {
        return SingleLineListItemViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: SingleLineListItemViewHolder, position: Int) {
        val data = getItem(position)
        holder.bindData(data.name, data.type.toString())
        holder.itemView.setOnClickListener { click(it, data) }
    }

}