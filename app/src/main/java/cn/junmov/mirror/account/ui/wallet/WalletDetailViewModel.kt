package cn.junmov.mirror.account.ui.wallet

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import cn.junmov.mirror.account.domain.FlowAccountUseCase
import cn.junmov.mirror.core.data.db.entity.Account
import cn.junmov.mirror.core.utility.MoneyUtils
import cn.junmov.mirror.core.utility.TimeUtils
import cn.junmov.mirror.core.data.db.view.ItemVoucher
import cn.junmov.mirror.voucher.domain.FlowAccountTradeLimitUseCase
import cn.junmov.mirror.account.domain.UpdateWalletBalanceUseCase
import cn.junmov.mirror.voucher.domain.CopyVoucherUseCase
import cn.junmov.mirror.voucher.domain.RemoveVoucherUseCase
import kotlinx.coroutines.launch

class WalletDetailViewModel @ViewModelInject constructor(
    private val flowWalletTradeLimit: FlowAccountTradeLimitUseCase,
    private val flowWallet: FlowAccountUseCase,
    private val updateBalance: UpdateWalletBalanceUseCase,
    private val copyVoucher: CopyVoucherUseCase,
    private val removeVoucher: RemoveVoucherUseCase
) : ViewModel() {

    private val _walletId = MutableLiveData<Long>()

    val wallet: LiveData<Account> = _walletId.switchMap { flowWallet(it).asLiveData() }

    val vouchers: LiveData<List<ItemVoucher>> = _walletId.switchMap {
        flowWalletTradeLimit(it, 10).asLiveData()
    }

    val balance: LiveData<String> = wallet.map { MoneyUtils.centToYuan(it.balance) }

    val createAt: LiveData<String> =
        wallet.map { "创建于${TimeUtils.dateToString(it.createAt.toLocalDate())}" }

    val lastSize: LiveData<Int> = vouchers.map { it.size }

    val allSize: LiveData<Int> = wallet.map { it.tradeCount }

    val message = MutableLiveData<Int>()

    fun loadData(id: Long) {
        _walletId.value = id
    }

    fun submitBalance(text: String) {
        if (!MoneyUtils.isFormat(text)) return
        val currentWallet = wallet.value ?: return
        val newBalance = MoneyUtils.yuanToCent(text)
        viewModelScope.launch {
            updateBalance(currentWallet, newBalance)
        }
    }

    fun copyVoucherItem(id: Long) {
        viewModelScope.launch { copyVoucher(id) }
    }

    fun removeVoucherItem(id: Long) {
        viewModelScope.launch { removeVoucher(id) }
    }

}