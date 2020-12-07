package cn.junmov.mirror.budget.ui

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import cn.junmov.mirror.core.data.model.Category
import cn.junmov.mirror.core.utility.navTo

class BudgetListAdapter : ListAdapter<Category, BudgetViewHolder>(DIFF_CALL_BACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BudgetViewHolder {
        return BudgetViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: BudgetViewHolder, position: Int) {
        val data = getItem(position)
        with(holder) {
            bind(data.account.name, data.budgetTotal, data.budgetUseAble)
            itemView.setOnClickListener {
                val navTo = if (data.account.tradAble) {
                    BudgetSecondaryFragmentDirections.actionBudgetSecondaryFragmentToBudgetDeltaFragment(
                        data.account.id, title = data.account.name
                    )
                } else {
                    BudgetFragmentDirections.actionPageBudgetToBudgetSecondaryFragment(
                        budgetId = data.account.id, title = data.account.name
                    )
                }
                it.navTo(navTo)
            }
        }
    }

    companion object {
        private val DIFF_CALL_BACK = object : DiffUtil.ItemCallback<Category>() {
            override fun areItemsTheSame(oldItem: Category, newItem: Category): Boolean {
                return oldItem.account.id == newItem.account.id
            }

            override fun areContentsTheSame(oldItem: Category, newItem: Category): Boolean {
                return oldItem.account == newItem.account
            }
        }
    }
}
