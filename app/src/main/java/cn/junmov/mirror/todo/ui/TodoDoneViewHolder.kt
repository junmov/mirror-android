package cn.junmov.mirror.todo.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import cn.junmov.mirror.R

class TodoDoneViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    val cbTodoDone: CheckBox = itemView.findViewById(R.id.cb_todo_done)
    val todoSummary: TextView = itemView.findViewById(R.id.todo_done_summary)

    companion object {
        fun create(parent: ViewGroup): TodoDoneViewHolder {
            return TodoDoneViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.list_item_todo_done, parent, false
                )
            )
        }
    }
}