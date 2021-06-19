package cn.junmov.mirror.core.widget

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import cn.junmov.mirror.R
import cn.junmov.mirror.core.data.model.FourCellUiModel

class FourCellListItemHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val primaryCell = itemView.findViewById<TextView>(R.id.list_item_four_cell_primary)
    private val secondaryCell = itemView.findViewById<TextView>(R.id.list_item_four_cell_secondary)
    private val topCell = itemView.findViewById<TextView>(R.id.list_item_four_cell_top)
    private val bottomCell = itemView.findViewById<TextView>(R.id.list_item_four_cell_bottom)

    fun bind(primary: String, secondary: String, top: String, bottom: String) {
        primaryCell.text = primary
        secondaryCell.text = secondary
        topCell.text = top
        bottomCell.text = bottom
    }

    fun bindUiModel(model: FourCellUiModel) {
        bind(model.primary(), model.secondary(), model.top(), model.bottom())
    }

    companion object {
        fun create(parent: ViewGroup): FourCellListItemHolder {
            return FourCellListItemHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.list_item_four_cell, parent, false
                )
            )
        }
    }

}