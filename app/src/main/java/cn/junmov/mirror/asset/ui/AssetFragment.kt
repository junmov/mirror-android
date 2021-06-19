package cn.junmov.mirror.asset.ui

import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import androidx.navigation.findNavController
import androidx.recyclerview.widget.ListAdapter
import cn.junmov.mirror.R
import cn.junmov.mirror.core.data.db.entity.Asset
import cn.junmov.mirror.core.widget.AbstractListFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AssetFragment : AbstractListFragment<Asset>() {

    private val viewModel: AssetViewModel by viewModels()

    override fun adapter(): ListAdapter<Asset, *> = AssetListAdapter { v, a ->
        v.findNavController().navigate(
            AssetFragmentDirections.actionScreenAssetToScreenAssetDetail(a.id, a.name)
        )
    }

    override fun data(): LiveData<List<Asset>> = viewModel.assets

    override fun hasMenu(): Boolean = true

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_asset, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.option_filter_asset -> {
                viewModel.toggleFilter()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}