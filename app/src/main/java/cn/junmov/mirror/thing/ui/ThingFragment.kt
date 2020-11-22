package cn.junmov.mirror.thing.ui

import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.ListAdapter
import cn.junmov.mirror.R
import cn.junmov.mirror.core.data.entity.Thing
import cn.junmov.mirror.core.widget.AbstractListFragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder
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
            R.id.option_create -> {
                showDialog()
                true
            }
            else -> false
        }
    }

    private fun showDialog() {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle("创建项目")
            .setView(R.layout.dialog_single_edit)
            .setPositiveButton("创建") { dialog, _ ->
                val text = (dialog as AlertDialog).findViewById<TextView>(R.id.dialog_single_text)
                if (text != null) {
                    viewModel.submitThing(text.text.toString())
                }
            }
            .setNegativeButton("取消", null)
            .show()
    }

}