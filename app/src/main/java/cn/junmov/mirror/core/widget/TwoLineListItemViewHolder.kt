package cn.junmov.mirror.core.widget

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import cn.junmov.mirror.R

class TwoLineListItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    val primaryText: TextView = itemView.findViewById(R.id.two_line_primary_text)
    val secondaryText: TextView = itemView.findViewById(R.id.two_line_secondary_text)
    val actionText: TextView = itemView.findViewById(R.id.two_line_action_text)

    fun bind(primary: String, secondary: String, top: String) {
        primaryText.text = primary
        secondaryText.text = secondary
        actionText.text = top
    }

    companion object {
        fun create(parent: ViewGroup): TwoLineListItemViewHolder {
            return TwoLineListItemViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.list_item_two_line, parent, false
                )
            )
        }
    }
}