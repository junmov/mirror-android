package cn.junmov.mirror.home.ui

import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.onNavDestinationSelected
import androidx.viewpager2.adapter.FragmentStateAdapter
import cn.junmov.mirror.R
import cn.junmov.mirror.core.widget.AbstractTabFragment
import cn.junmov.mirror.user.ui.ProfileViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeTabFragment : AbstractTabFragment() {

    private val viewModel: ProfileViewModel by activityViewModels()

    override fun whenAfterCreateView() {
        viewModel.isSigned.observe(viewLifecycleOwner) { isSigned ->
            if (!isSigned) {
                findNavController().navigate(R.id.sign_in_fragment)
            }
        }
    }

    override fun pagerAdapter(): FragmentStateAdapter = HomeTabAdapter(this)

    override fun tabTitle(position: Int): String? = when (position) {
        HOME_TODO_PAGE_INDEX -> "今日待办"
        HOME_TRADE_PAGE_INDEX -> "最近交易"
        else -> null
    }

    override fun hasMenu(): Boolean = true

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_home, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val navController = findNavController()
        return item.onNavDestinationSelected(navController) || super.onOptionsItemSelected(item)
    }

}