package cn.junmov.mirror.wallet.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import cn.junmov.mirror.core.data.db.entity.Account
import cn.junmov.mirror.core.utility.MoneyUtils
import cn.junmov.mirror.core.utility.TimeUtils
import cn.junmov.mirror.voucher.data.ItemVoucher
import cn.junmov.mirror.wallet.domain.FlowWalletTradeLimitUseCase
import cn.junmov.mirror.wallet.domain.FlowWalletUseCase
import cn.junmov.mirror.wallet.domain.UpdateWalletBalanceUseCase
import kotlinx.coroutines.launch

class WalletDetailViewModel @ViewModelInject constructor(
    private val flowWalletTradeLimit: FlowWalletTradeLimitUseCase,
    private val flowWallet: FlowWalletUseCase,
    private val updateBalance: UpdateWalletBalanceUseCase
) : ViewModel() {

    private val _walletId = MutableLiveData<Long>()

    val wallet: LiveData<Account> = _walletId.switchMap { flowWallet(it).asLiveData() }

    val vouchers: LiveData<List<ItemVoucher>> = _walletId.switchMap {
        flowWalletTradeLimit(it, 10).asLiveData()
    }

    val balance: LiveData<String> = wallet.map { MoneyUtils.centToYuan(it.base) }

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

}