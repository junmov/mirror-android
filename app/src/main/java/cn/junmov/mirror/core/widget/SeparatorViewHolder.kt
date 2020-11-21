package cn.junmov.mirror.core.widget

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import cn.junmov.mirror.R
import cn.junmov.mirror.core.adapter.SingleLineModel
import cn.junmov.mirror.core.adapter.TwoLineModel

class SeparatorViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val separatorView: TextView = itemView.findViewById(R.id.list_separator)

    fun bind(separator: SingleLineModel.Separator) {
        this.separatorView.text = separator.description
    }

    fun bind(separator: TwoLineModel.Separator) {
        this.separatorView.text = separator.description
    }

    companion object {
        fun create(viewGroup: ViewGroup): SeparatorViewHolder {
            return SeparatorViewHolder(
                LayoutInflater.from(viewGroup.context).inflate(
                    R.layout.list_separator, viewGroup, false
                )
            )
        }
    }
}