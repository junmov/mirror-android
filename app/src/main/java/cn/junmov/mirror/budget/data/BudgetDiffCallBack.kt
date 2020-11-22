package cn.junmov.mirror.budget.data

import androidx.recyclerview.widget.DiffUtil
import cn.junmov.mirror.core.data.entity.Budget

object BudgetDiffCallBack : DiffUtil.ItemCallback<Budget>() {
    override fun areItemsTheSame(oldItem: Budget, newItem: Budget): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Budget, newItem: Budget): Boolean {
        return oldItem == newItem
    }
}