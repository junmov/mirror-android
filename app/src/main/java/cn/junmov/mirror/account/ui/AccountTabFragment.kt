package cn.junmov.mirror.account.ui

import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.adapter.FragmentStateAdapter
import cn.junmov.mirror.R
import cn.junmov.mirror.core.widget.AbstractTabFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AccountTabFragment : AbstractTabFragment() {

    override fun pagerAdapter(): FragmentStateAdapter = AccountTabAdapter(this)

    override fun tabTitle(position: Int): String? = when (position) {
        BALANCE_PAGE_INDEX -> "收支账户"
        CATEGORY_PAGE_INDEX -> "分类账户"
        else -> null
    }

    override fun hasMenu(): Boolean = true

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_create, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.option_create -> {
                findNavController().navigate(
                    AccountTabFragmentDirections.actionPageAccountToAccountFormDialog()
                )
                true
            }
            else -> false
        }
    }

}