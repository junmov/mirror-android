package cn.junmov.mirror.statistics.ui

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.paging.PagingData
import androidx.paging.PagingDataAdapter
import cn.junmov.mirror.MainNavDirections
import cn.junmov.mirror.core.adapter.TwoLineModel
import cn.junmov.mirror.core.adapter.TwoLinePagingAdapter
import cn.junmov.mirror.core.widget.PagedListFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.Flow

@AndroidEntryPoint
class AuditPlanFragment : PagedListFragment<TwoLineModel>() {

    private val viewModel: AuditPlanViewModel by viewModels()

    override fun adapter(): PagingDataAdapter<TwoLineModel, *> = TwoLinePagingAdapter { id, _ ->
        findNavController().navigate(
            MainNavDirections.actionGlobalVoucherDetailFragment(id)
        )
    }


    override fun pagedData(): Flow<PagingData<TwoLineModel>> = viewModel.vouchers

}