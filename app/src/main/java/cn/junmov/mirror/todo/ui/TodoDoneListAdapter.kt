package cn.junmov.mirror.todo.ui

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import cn.junmov.mirror.core.data.db.entity.Todo
import cn.junmov.mirror.todo.data.TodoDiffCallBack

class TodoDoneListAdapter(
    private val done: (Todo) -> Unit
) : ListAdapter<Todo, TodoDoneViewHolder>(TodoDiffCallBack) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoDoneViewHolder {
        return TodoDoneViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: TodoDoneViewHolder, position: Int) {
        val data = getItem(position)
        with(holder) {
            todoSummary.text = data.summary
            cbTodoDone.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) done(data)
            }
        }
    }
}