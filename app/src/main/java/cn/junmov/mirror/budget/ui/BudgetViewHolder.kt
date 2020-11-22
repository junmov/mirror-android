package cn.junmov.mirror.budget.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import cn.junmov.mirror.R

class BudgetViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    val labelAccount = itemView.findViewById<TextView>(R.id.label_budget_account_name)
    val labelUseAble = itemView.findViewById<TextView>(R.id.label_budget_account_use_able)
    val labelTotal = itemView.findViewById<TextView>(R.id.label_budget_account_total)
    val progress = itemView.findViewById<ProgressBar>(R.id.pb_budget_account)

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