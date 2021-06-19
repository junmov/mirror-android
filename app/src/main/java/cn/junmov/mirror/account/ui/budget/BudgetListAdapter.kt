package cn.junmov.mirror.account.ui.budget

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import cn.junmov.mirror.account.data.AccountDiffCallBack
import cn.junmov.mirror.core.data.db.entity.Account

class BudgetListAdapter(
    private val itemClick: (View, Account) -> Any
) : ListAdapter<Account, BudgetViewHolder>(AccountDiffCallBack) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BudgetViewHolder {
        return BudgetViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: BudgetViewHolder, position: Int) {
        val data = getItem(position)
        with(holder) {
            bind(data.name, data.budget, data.budgetUseAble())
            itemView.setOnClickListener { itemClick(it, data) }
        }
    }

}
