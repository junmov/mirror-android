package cn.junmov.mirror.todo.ui

import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.ListAdapter
import cn.junmov.mirror.R
import cn.junmov.mirror.core.data.entity.Todo
import cn.junmov.mirror.core.utility.navTo
import cn.junmov.mirror.core.widget.AbstractListFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TodoFragment : AbstractListFragment<Todo>() {

    private val viewModel: TodoViewModel by viewModels()

    override fun adapter(): ListAdapter<Todo, *> = TodoEnableListAdapter { id, enable ->
        viewModel.changeScheduleState(id, enable)
    }

    override fun data(): LiveData<List<Todo>> = viewModel.todoList

    override fun hasMenu(): Boolean = true

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_create, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.option_create -> navTo(TodoFragmentDirections.actionTodoFragmentToTodoFormDialog())
            else -> super.onOptionsItemSelected(item)
        }
    }

}