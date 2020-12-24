package cn.junmov.mirror.budget.ui

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import cn.junmov.mirror.core.data.db.entity.Account
import cn.junmov.mirror.core.data.model.AccountDiffCallBack
import cn.junmov.mirror.core.utility.navTo

class BudgetListAdapter : ListAdapter<Account, BudgetViewHolder>(AccountDiffCallBack) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BudgetViewHolder {
        return BudgetViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: BudgetViewHolder, position: Int) {
        val data = getItem(position)
        with(holder) {
            val direction = if (data.tradAble) {
                BudgetSecondaryFragmentDirections.actionBudgetSecondaryFragmentToBudgetDeltaFragment(
                    data.id, title = data.name
                )
            } else {
                BudgetFragmentDirections.actionPageBudgetToBudgetSecondaryFragment(
                    budgetId = data.id, title = data.name
                )
            }
            bind(data.name, data.base + data.inflow, data.base + data.inflow - data.outflow)
            itemView.navTo(direction)
        }
    }

}
