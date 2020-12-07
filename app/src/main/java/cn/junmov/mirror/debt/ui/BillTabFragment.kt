package cn.junmov.mirror.debt.ui

import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.adapter.FragmentStateAdapter
import cn.junmov.mirror.R
import cn.junmov.mirror.core.widget.AbstractTabFragment

class BillTabFragment : AbstractTabFragment() {

    override fun pagerAdapter(): FragmentStateAdapter = BillTabAdapter(this)

    override fun tabTitle(position: Int): String? = when (position) {
        DEBT_PAGE_INDEX -> "借款"
        BILL_PAGE_INDEX -> "还款"
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
                    BillTabFragmentDirections.actionBillTabFragmentToDebtFormDialog()
                )
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}