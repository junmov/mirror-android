package cn.junmov.mirror.asset.ui

import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.ListAdapter
import cn.junmov.mirror.R
import cn.junmov.mirror.core.data.db.entity.Asset
import cn.junmov.mirror.core.utility.showInputDialog
import cn.junmov.mirror.core.widget.AbstractListFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AssetFragment : AbstractListFragment<Asset>() {

    private val viewModel: AssetViewModel by viewModels()

    override fun adapter(): ListAdapter<Asset, *> = AssetListAdapter()

    override fun data(): LiveData<List<Asset>> = viewModel.assets

    override fun hasMenu(): Boolean = true

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_asset, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.option_add_asset -> showInputDialog("添加资产", "添加") { viewModel.submitAsset(it) }
            R.id.option_filter_asset -> {
                filterData()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun filterData() {
        viewModel.toggleFilter()
    }

}