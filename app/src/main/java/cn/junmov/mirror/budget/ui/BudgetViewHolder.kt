package cn.junmov.mirror.budget.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import cn.junmov.mirror.R
import cn.junmov.mirror.core.utility.MoneyUtils
import cn.junmov.mirror.core.utility.setString

class BudgetViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val labelAccount = itemView.findViewById<TextView>(R.id.label_budget_account_name)
    private val labelUseAble = itemView.findViewById<TextView>(R.id.label_budget_account_use_able)
    private val labelTotal = itemView.findViewById<TextView>(R.id.label_budget_account_total)
    private val progress = itemView.findViewById<ProgressBar>(R.id.pb_budget_account)

    fun bind(name: String, total: Int, useAble: Int) {
        labelAccount.text = name
        labelTotal.setString(R.string.budget_account_total, MoneyUtils.centToYuan(total))
        labelUseAble.setString(R.string.budget_account_use_able, MoneyUtils.centToYuan(useAble))
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