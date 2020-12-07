package cn.junmov.mirror.wallet.ui

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import cn.junmov.mirror.core.data.model.Wallet
import cn.junmov.mirror.core.utility.MoneyUtils
import cn.junmov.mirror.core.utility.navTo
import cn.junmov.mirror.core.widget.TwoLineListItemViewHolder

class WalletListAdapter : ListAdapter<Wallet, TwoLineListItemViewHolder>(DIFF_CALL_BACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TwoLineListItemViewHolder {
        return TwoLineListItemViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: TwoLineListItemViewHolder, position: Int) {
        val data = getItem(position)
        with(holder) {
            bind(
                data.account.name, data.account.type.toString(),
                MoneyUtils.centToYuan(data.balance())
            )
            itemView.navTo(
                WalletFragmentDirections.actionPageAccountToAccountDetailFragment(
                    data.account.id, data.account.name
                )
            )
        }
    }

    companion object {
        private
        val DIFF_CALL_BACK = object : DiffUtil.ItemCallback<Wallet>() {
            override fun areItemsTheSame(oldItem: Wallet, newItem: Wallet): Boolean {
                return oldItem.account.id == newItem.account.id
            }

            override fun areContentsTheSame(oldItem: Wallet, newItem: Wallet): Boolean {
                return oldItem.account == newItem.account
            }

        }
    }

}