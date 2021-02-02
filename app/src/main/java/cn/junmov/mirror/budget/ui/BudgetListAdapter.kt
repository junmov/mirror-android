package cn.junmov.mirror.budget.ui

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import cn.junmov.mirror.core.data.db.entity.Account
import cn.junmov.mirror.core.data.model.AccountDiffCallBack

class BudgetListAdapter(
    private val itemClick: (View, Account) -> Any
) : ListAdapter<Account, BudgetViewHolder>(AccountDiffCallBack) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BudgetViewHolder {
        return BudgetViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: BudgetViewHolder, position: Int) {
        val data = getItem(position)
        with(holder) {
            bind(data.name, data.base, data.base + data.inflow - data.outflow)
            itemView.setOnClickListener { itemClick(it, data) }
        }
    }

}
