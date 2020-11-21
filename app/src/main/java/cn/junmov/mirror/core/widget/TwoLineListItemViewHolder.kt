package cn.junmov.mirror.core.widget

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import cn.junmov.mirror.R
import cn.junmov.mirror.core.adapter.TwoLineModel

class TwoLineListItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val primary: TextView = itemView.findViewById(R.id.two_line_primary_text)
    private val secondary: TextView = itemView.findViewById(R.id.two_line_secondary_text)
    private val action: TextView = itemView.findViewById(R.id.two_line_action_text)
    private var onClick: View.OnClickListener? = null

    fun bind(data: TwoLineModel.UiData) {
        primary.text = data.primary
        secondary.text = data.secondary
        action.text = data.action
    }

    fun onListItemClick(click: View.OnClickListener) {
        onClick = click
        itemView.setOnClickListener(onClick)
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