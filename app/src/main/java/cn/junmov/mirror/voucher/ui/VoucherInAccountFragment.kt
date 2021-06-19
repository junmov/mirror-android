package cn.junmov.mirror.voucher.ui

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.paging.PagingData
import androidx.paging.PagingDataAdapter
import cn.junmov.mirror.core.widget.PagedListFragment
import cn.junmov.mirror.core.data.db.view.ItemVoucher
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.Flow

/**
 * 统计某账户参与的交易
 */
@AndroidEntryPoint
class VoucherInAccountFragment : PagedListFragment<ItemVoucher>() {

    private val viewModel: VoucherInAccountViewModel by viewModels()

    private val args: VoucherInAccountFragmentArgs by navArgs()

    override fun adapter(): PagingDataAdapter<ItemVoucher, *> = VoucherPagingAdapter(
        onClickCopy = { viewModel.copyVoucherItem(it) },
        onClickRemove = { viewModel.removeVoucherItem(it) }
    )

    override fun pagedData(): Flow<PagingData<ItemVoucher>> = viewModel.loadData(args.accountId)
}