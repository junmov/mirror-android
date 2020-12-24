package cn.junmov.mirror.home.ui

import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.ListAdapter
import cn.junmov.mirror.core.data.db.entity.Todo
import cn.junmov.mirror.core.widget.AbstractListFragment
import cn.junmov.mirror.todo.ui.TodoDoneListAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeToDoFragment : AbstractListFragment<Todo>() {

    private val viewModel: HomeToDoViewModel by viewModels()

    override fun adapter(): ListAdapter<Todo, *> = TodoDoneListAdapter { viewModel.done(it) }

    override fun data(): LiveData<List<Todo>> = viewModel.toDoList
}