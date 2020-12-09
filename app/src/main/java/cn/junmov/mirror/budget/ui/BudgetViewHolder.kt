package cn.junmov.mirror.budget.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import cn.junmov.mirror.R
import cn.junmov.mirror.core.utility.MoneyUtils

class BudgetViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val labelAccount = itemView.findViewById<TextView>(R.id.label_budget_account_name)
    private val labelUseAble = itemView.findViewById<TextView>(R.id.label_budget_account_use_able)
    private val progress = itemView.findViewById<ProgressBar>(R.id.pb_budget_account)

    fun bind(name: String, total: Int, useAble: Int) {
        labelAccount.text = name
        labelUseAble.text = MoneyUtils.centToYuan(useAble)
        progress.max = total
        progress.progress = useAble
    }

    companion object {
        fun create(parent: ViewGroup): BudgetViewHolder {
            return BudgetViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.list_item_budget, parent, false
                )
            )
        }
    }

}