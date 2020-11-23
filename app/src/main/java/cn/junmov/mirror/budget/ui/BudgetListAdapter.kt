package cn.junmov.mirror.budget.ui

import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.ListAdapter
import cn.junmov.mirror.R
import cn.junmov.mirror.budget.data.BudgetDiffCallBack
import cn.junmov.mirror.core.data.entity.Budget
import cn.junmov.mirror.core.utility.MoneyUtils
import cn.junmov.mirror.core.utility.setString

class BudgetListAdapter : ListAdapter<Budget, BudgetViewHolder>(BudgetDiffCallBack) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BudgetViewHolder {
        return BudgetViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: BudgetViewHolder, position: Int) {
        val data = getItem(position)
        with(holder) {
            val total = data.total
            val useAble = data.total - data.used
            labelAccount.text = data.accountName
            labelTotal.setString(R.string.budget_account_total, MoneyUtils.centToYuan(total))
            labelUseAble.setString(R.string.budget_account_use_able, MoneyUtils.centToYuan(useAble))
            progress.max = total
            progress.progress = useAble
            itemView.setOnClickListener {
                if (data.parentId == 0L) {
                    it.findNavController().navigate(
                        BudgetFragmentDirections.actionPageBudgetToBudgetSecondaryFragment(
                            budgetId = data.id, title = data.accountName
                        )
                    )
                } else {
                    it.findNavController().navigate(
                        BudgetSecondaryFragmentDirections.actionBudgetSecondaryFragmentToBudgetFormDialog(
                            data.id
                        )
                    )
                }
            }
        }
    }
}
