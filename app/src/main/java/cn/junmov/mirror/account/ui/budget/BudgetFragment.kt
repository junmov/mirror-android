package cn.junmov.mirror.account.ui.budget

import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.ListAdapter
import cn.junmov.mirror.R
import cn.junmov.mirror.core.data.db.entity.Account
import cn.junmov.mirror.core.utility.navTo
import cn.junmov.mirror.core.widget.AbstractListFragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BudgetFragment : AbstractListFragment<Account>() {

    private val viewModel: BudgetViewModel by viewModels()

    override fun adapter(): ListAdapter<Account, *> = BudgetListAdapter { v, budget ->
        MaterialAlertDialogBuilder(v.context)
            .setTitle("调整预算")
            .setView(R.layout.dialog_single_edit)
            .setPositiveButton("调整") { dialog, _ ->
                val input = (dialog as AlertDialog).findViewById<TextView>(R.id.dialog_single_text)
                viewModel.submitBudget(budget.id, input?.text.toString())
            }
            .setNegativeButton("取消", null)
            .show()
    }

    override fun data(): LiveData<List<Account>> = viewModel.budgets

    override fun hasMenu(): Boolean = true

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_create, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.option_create -> navTo(BudgetFragmentDirections.actionScreenBudgetToScreenCreateBudget())
            else -> super.onOptionsItemSelected(item)
        }
    }

}