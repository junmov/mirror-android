package cn.junmov.mirror.statistics.ui

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.paging.PagingData
import androidx.paging.PagingDataAdapter
import cn.junmov.mirror.MainNavDirections
import cn.junmov.mirror.core.adapter.TwoLineModel
import cn.junmov.mirror.core.adapter.TwoLinePagingAdapter
import cn.junmov.mirror.core.widget.PagedListFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.Flow

/**
 * 统计某账户参与的交易
 */
@AndroidEntryPoint
class AccountTradingFragment : PagedListFragment<TwoLineModel>() {

    private val viewModel: AccountTradingViewModel by viewModels()

    private val args: AccountTradingFragmentArgs by navArgs()

    override fun adapter(): PagingDataAdapter<TwoLineModel, *> = TwoLinePagingAdapter { id, _ ->
        findNavController().navigate(
            MainNavDirections.actionGlobalVoucherDetailFragment(id)
        )
    }

    override fun pagedData(): Flow<PagingData<TwoLineModel>> = viewModel.loadData(args.accountId)
}