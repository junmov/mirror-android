package cn.junmov.mirror.asset.ui

import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.ListAdapter
import cn.junmov.mirror.R
import cn.junmov.mirror.core.data.entity.Asset
import cn.junmov.mirror.core.utility.setupInputDialog
import cn.junmov.mirror.core.utility.showInputDialog
import cn.junmov.mirror.core.widget.AbstractListFragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AssetFragment : AbstractListFragment<Asset>() {

    private val viewModel: AssetViewModel by viewModels()

    override fun adapter(): ListAdapter<Asset, *> = AssetListAdapter()

    override fun data(): LiveData<List<Asset>> = viewModel.assets

    override fun hasMenu(): Boolean = true

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_create, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.option_create -> {
                showInputDialog("添加资产", "添加") { viewModel.submitAsset(it) }
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}