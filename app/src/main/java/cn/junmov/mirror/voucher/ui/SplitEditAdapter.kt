package cn.junmov.mirror.voucher.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import cn.junmov.mirror.R
import cn.junmov.mirror.core.data.db.entity.Split
import cn.junmov.mirror.core.utility.MoneyUtils

class SplitEditAdapter(
    private val viewModel: SplitFormViewModel
) : ListAdapter<Split, SplitEditAdapter.SplitViewHolder>(DIFF_CALL_BACK) {

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): SplitViewHolder = SplitViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.list_item_split, parent, false)
    )

    override fun onBindViewHolder(holder: SplitViewHolder, position: Int) {
        val data = getItem(position)
        val text = if (data.debit) "借: ${data.accountName}" else "贷: ${data.accountName}"
        holder.primary.text = text
        holder.action.text = MoneyUtils.centToYuan(data.amount)
        holder.btn.setOnClickListener {
            viewModel.removeSplit(data)
        }
        holder.itemView.setOnClickListener {
            viewModel.editSplit(data)
        }
    }

    class SplitViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val primary: TextView = itemView.findViewById(R.id.label_split_summary)
        val action: TextView = itemView.findViewById(R.id.label_split_amount)
        val btn: ImageView = itemView.findViewById(R.id.btn_remove_split)
    }

    companion object {
        private val DIFF_CALL_BACK = object : DiffUtil.ItemCallback<Split>() {
            override fun areItemsTheSame(oldItem: Split, newItem: Split): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Split, newItem: Split): Boolean {
                return oldItem == newItem
            }
        }
    }
}