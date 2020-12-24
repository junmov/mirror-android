package cn.junmov.mirror.todo.data

import androidx.recyclerview.widget.DiffUtil
import cn.junmov.mirror.core.data.db.entity.Todo

object TodoDiffCallBack : DiffUtil.ItemCallback<Todo>(){
    override fun areItemsTheSame(oldItem: Todo, newItem: Todo): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Todo, newItem: Todo): Boolean {
        return oldItem == newItem
    }
}