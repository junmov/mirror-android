package cn.junmov.mirror.account.ui.wallet

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import cn.junmov.mirror.core.data.db.entity.Account
import cn.junmov.mirror.account.data.AccountDiffCallBack
import cn.junmov.mirror.core.utility.MoneyUtils
import cn.junmov.mirror.core.utility.navTo
import cn.junmov.mirror.core.widget.TwoLineListItemViewHolder

class WalletListAdapter : ListAdapter<Account, TwoLineListItemViewHolder>(AccountDiffCallBack) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TwoLineListItemViewHolder {
        return TwoLineListItemViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: TwoLineListItemViewHolder, position: Int) {
        val data = getItem(position)
        with(holder) {
            bind(
                data.name, data.type.toString(),
                MoneyUtils.centToYuan(data.balance)
            )
            itemView.navTo(
                WalletFragmentDirections.actionScreenWalletToScreenAccountDetail(
                    data.id, data.name
                )
            )
        }
    }

}