package cn.junmov.mirror.account.ui.setting

import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.ListAdapter
import cn.junmov.mirror.R
import cn.junmov.mirror.core.data.db.entity.Account
import cn.junmov.mirror.core.utility.navTo
import cn.junmov.mirror.core.widget.AbstractListFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AccountListFragment : AbstractListFragment<Account>() {

    private val viewModel: AccountListViewModel by viewModels()

    override fun adapter(): ListAdapter<Account, *> = AccountListAdapter { _, _ -> }

    override fun data(): LiveData<List<Account>> = viewModel.accounts

    override fun hasMenu(): Boolean = true

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_create, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.option_create -> navTo(AccountListFragmentDirections.actionScreenAccountListToScreenAccountForm())
            else -> super.onOptionsItemSelected(item)
        }
    }

}