package cn.junmov.mirror.todo.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import cn.junmov.mirror.R
import cn.junmov.mirror.core.data.db.entity.Todo
import cn.junmov.mirror.core.utility.TimeUtils
import cn.junmov.mirror.core.utility.setString
import cn.junmov.mirror.todo.data.TodoDiffCallBack

class TodoEnableListAdapter(
    private val change: (Todo, Boolean) -> Unit
) : ListAdapter<Todo, TodoEnableViewHolder>(TodoDiffCallBack) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoEnableViewHolder {
        return TodoEnableViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.list_item_todo_enable, parent, false)
        )
    }

    override fun onBindViewHolder(holder: TodoEnableViewHolder, position: Int) {
        getItem(position).let { todo ->
            with(holder) {
                summary.text = todo.summary
                nextRun.setString(
                    R.string.todo_next_run, TimeUtils.dateToString(todo.runAt)
                )
                count.setString(R.string.todo_done_times, todo.doneTimes)
                enable.isChecked = todo.enabled
                enable.setOnCheckedChangeListener { _, isChecked ->
                    change(todo, isChecked)
                }
            }
        }
    }

}