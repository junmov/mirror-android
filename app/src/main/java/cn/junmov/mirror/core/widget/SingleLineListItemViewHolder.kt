package cn.junmov.mirror.core.widget

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import cn.junmov.mirror.R

class SingleLineListItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val primary: TextView = itemView.findViewById(R.id.single_line_primary_text)
    private val action: TextView = itemView.findViewById(R.id.single_line_action_text)
    private var onClick: View.OnClickListener? = null

    fun bindData(first: String, second: String) {
        primary.text = first
        action.text = second
    }

    companion object {
        fun create(parent: ViewGroup): SingleLineListItemViewHolder {
            return SingleLineListItemViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.list_item_single_line, parent, false
                )
            )
        }
    }
}