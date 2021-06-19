package cn.junmov.mirror.debt.ui

import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import androidx.navigation.findNavController
import androidx.recyclerview.widget.ListAdapter
import cn.junmov.mirror.R
import cn.junmov.mirror.core.data.db.entity.Debt
import cn.junmov.mirror.core.widget.AbstractListFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BorrowFragment : AbstractListFragment<Debt>() {

    private val viewModel: DebtViewModel by viewModels()

    override fun adapter(): ListAdapter<Debt, *> = DebtListAdapter {data,view->
        view.findNavController().navigate(
            BorrowFragmentDirections.actionScreenBorrowListToScreenDebtDetail(data.id)
        )
    }

    override fun data(): LiveData<List<Debt>> = viewModel.debts

    override fun hasMenu(): Boolean = true

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_debt, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.option_filter_debt -> {
                viewModel.toggleFilter()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}