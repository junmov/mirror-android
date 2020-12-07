package cn.junmov.mirror.home.ui

import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.ListAdapter
import cn.junmov.mirror.core.widget.AbstractListFragment
import cn.junmov.mirror.voucher.data.ItemVoucher
import cn.junmov.mirror.voucher.ui.VoucherListAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeTradeFragment : AbstractListFragment<ItemVoucher>() {

    private val viewModel: HomeTradeViewModel by viewModels()

    override fun adapter(): ListAdapter<ItemVoucher, *> = VoucherListAdapter()

    override fun data(): LiveData<List<ItemVoucher>> = viewModel.vouchers

}