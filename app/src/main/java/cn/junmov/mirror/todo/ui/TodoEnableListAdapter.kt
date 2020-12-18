package cn.junmov.mirror.todo.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import cn.junmov.mirror.R
import cn.junmov.mirror.core.data.entity.Todo
import cn.junmov.mirror.core.utility.TimeUtils
import cn.junmov.mirror.core.utility.setString
import cn.junmov.mirror.todo.data.TodoDiffCallBack

class TodoEnableListAdapter(
    private val change: (Long, Boolean) -> Unit
) : ListAdapter<Todo, TodoEnableViewHolder>(TodoDiffCallBack) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoEnableViewHolder {
        return TodoEnableViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.list_item_todo_enable, parent, false)
        )
    }

    override fun onBindViewHolder(holder: TodoEnableViewHolder, position: Int) {
        getItem(position).let {
            with(holder) {
                summary.text = it.summary
                nextRun.setString(
                    R.string.todo_next_run, TimeUtils.dateToString(it.runAt)
                )
                count.setString(R.string.todo_done_times, it.doneTimes)
                enable.isChecked = it.enabled
                enable.setOnCheckedChangeListener { _, isChecked ->
                    change(it.id, isChecked)
                }
            }
        }
    }

}