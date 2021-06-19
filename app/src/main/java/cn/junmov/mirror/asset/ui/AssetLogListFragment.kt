package cn.junmov.mirror.asset.ui

import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.ListAdapter
import cn.junmov.mirror.MainNavDirections
import cn.junmov.mirror.R
import cn.junmov.mirror.core.data.db.entity.AssetLog
import cn.junmov.mirror.core.utility.navTo
import cn.junmov.mirror.core.utility.showInputDialog
import cn.junmov.mirror.core.widget.AbstractListFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AssetLogListFragment : AbstractListFragment<AssetLog>() {

    private val viewModel: AssetLogListViewModel by viewModels()

    override fun adapter(): ListAdapter<AssetLog, *> = AssetLogListAdapter { _, a ->
        if (!a.success) {
            showInputDialog("添加交易发生金额", "提交") { amount ->
                viewModel.submitOrderAmount(a.id, amount)
            }
        }
    }

    override fun data(): LiveData<List<AssetLog>> = viewModel.assetLogs

    override fun hasMenu(): Boolean = true

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_create, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.option_create -> navTo(MainNavDirections.actionGlobalScreenAssetLogForm())
            else -> super.onOptionsItemSelected(item)
        }
    }

}