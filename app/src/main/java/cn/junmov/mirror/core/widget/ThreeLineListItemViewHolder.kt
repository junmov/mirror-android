package cn.junmov.mirror.core.widget

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import cn.junmov.mirror.R

class ThreeLineListItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    val primary: TextView = itemView.findViewById(R.id.three_line_primary_text)
    val secondary: TextView = itemView.findViewById(R.id.three_line_secondary_text)
    val tertiary: TextView = itemView.findViewById(R.id.three_line_tertiary_text)
    val action: TextView = itemView.findViewById(R.id.three_line_action_text)

    companion object {
        fun create(parent: ViewGroup): ThreeLineListItemViewHolder {
            return ThreeLineListItemViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.list_item_three_line, parent, false
                )
            )
        }
    }
}