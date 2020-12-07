package cn.junmov.mirror.debt.ui

import androidx.fragment.app.viewModels
import androidx.paging.PagingData
import androidx.paging.PagingDataAdapter
import cn.junmov.mirror.core.adapter.SingleLineModel
import cn.junmov.mirror.core.adapter.SingleLinePagingAdapter
import cn.junmov.mirror.core.widget.PagedListFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.Flow

@AndroidEntryPoint
class BillFragment : PagedListFragment<SingleLineModel>() {

    private val viewModel: BillViewModel by viewModels()

    override fun adapter(): PagingDataAdapter<SingleLineModel, *> = SingleLinePagingAdapter(null)

    override fun pagedData(): Flow<PagingData<SingleLineModel>> = viewModel.bills
}