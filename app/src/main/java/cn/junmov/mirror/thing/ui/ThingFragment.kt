package cn.junmov.mirror.thing.ui

import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.ListAdapter
import cn.junmov.mirror.R
import cn.junmov.mirror.core.data.entity.Thing
import cn.junmov.mirror.core.utility.showInputDialog
import cn.junmov.mirror.core.widget.AbstractListFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ThingFragment : AbstractListFragment<Thing>() {

    private val viewModel: ThingViewModel by viewModels()

    override fun adapter(): ListAdapter<Thing, *> = ThingListAdapter()

    override fun data(): LiveData<List<Thing>> = viewModel.things

    override fun hasMenu(): Boolean = true

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_create, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.option_create -> showInputDialog("创建项目账本", "创建") { viewModel.submitName(it) }
            else -> false
        }
    }

}