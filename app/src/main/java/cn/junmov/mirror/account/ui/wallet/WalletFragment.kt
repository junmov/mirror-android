package cn.junmov.mirror.account.ui.wallet

import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.ListAdapter
import cn.junmov.mirror.core.data.db.entity.Account
import cn.junmov.mirror.core.widget.AbstractListFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WalletFragment : AbstractListFragment<Account>() {

    private val viewModel: WalletViewModel by viewModels()

    override fun adapter(): ListAdapter<Account, *> = WalletListAdapter()

    override fun data(): LiveData<List<Account>> = viewModel.wallets

}