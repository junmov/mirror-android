package cn.junmov.mirror.voucher.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import cn.junmov.mirror.core.data.db.view.ItemVoucher
import cn.junmov.mirror.voucher.domain.CopyVoucherUseCase
import cn.junmov.mirror.voucher.domain.PagingVoucherInAccountUseCase
import cn.junmov.mirror.voucher.domain.RemoveVoucherUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class VoucherInAccountViewModel @ViewModelInject constructor(
    private val pagingAccountTrading: PagingVoucherInAccountUseCase,
    private val copyVoucher: CopyVoucherUseCase,
    private val removeVoucher: RemoveVoucherUseCase
) : ViewModel() {

    fun loadData(accountId: Long): Flow<PagingData<ItemVoucher>> {
        return pagingAccountTrading(accountId).cachedIn(viewModelScope)
    }

    fun removeVoucherItem(id: Long) {
        viewModelScope.launch { removeVoucher(id) }
    }

    fun copyVoucherItem(id: Long) {
        viewModelScope.launch { copyVoucher(id) }
    }
}