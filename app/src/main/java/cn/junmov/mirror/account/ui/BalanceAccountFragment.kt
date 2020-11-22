package cn.junmov.mirror.account.ui

import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.ListAdapter
import cn.junmov.mirror.core.data.entity.Account
import cn.junmov.mirror.core.widget.AbstractListFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BalanceAccountFragment : AbstractListFragment<Account>() {

    private val viewModel: BalanceAccountViewModel by viewModels()

    override fun adapter(): ListAdapter<Account, *> = ParentAccountListAdapter { id, title ->
    }

    override fun data(): LiveData<List<Account>> = viewModel.accounts

}