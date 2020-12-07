package cn.junmov.mirror.core.widget

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import cn.junmov.mirror.R
import cn.junmov.mirror.core.adapter.ThreeLineData

class ThreeLineListItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    val primary: TextView = itemView.findViewById(R.id.three_line_primary_text)
    val secondary: TextView = itemView.findViewById(R.id.three_line_secondary_text)
    val tertiary: TextView = itemView.findViewById(R.id.three_line_tertiary_text)
    val action: TextView = itemView.findViewById(R.id.three_line_action_text)

    private var onClick: View.OnClickListener? = null

    fun bind(data: ThreeLineData) {
        primary.text = data.primary
        secondary.text = data.secondary
        tertiary.text = data.tertiary
        action.text = data.action
    }

    fun onListItemClick(click: View.OnClickListener) {
        onClick = click
        itemView.setOnClickListener(onClick)
    }

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