package cn.junmov.mirror.asset.ui

import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.ListAdapter
import cn.junmov.mirror.core.data.db.entity.AssetLog
import cn.junmov.mirror.core.widget.AbstractListFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AssetDetailFragment : AbstractListFragment<AssetLog>() {

    private val viewModel: AssetDetailViewModel by viewModels()

    private val args: AssetDetailFragmentArgs by navArgs()

    override fun whenAfterCreateView() {
        viewModel.loadData(args.assetId)
    }

    override fun adapter(): ListAdapter<AssetLog, *> = AssetLogListAdapter { v, a ->
    }

    override fun data(): LiveData<List<AssetLog>> = viewModel.assetLogs

}