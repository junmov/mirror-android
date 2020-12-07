package cn.junmov.mirror.wallet.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import cn.junmov.mirror.core.data.model.Wallet
import cn.junmov.mirror.core.utility.MoneyUtils
import cn.junmov.mirror.core.utility.TimeUtils
import cn.junmov.mirror.voucher.data.ItemVoucher
import cn.junmov.mirror.wallet.domain.FlowWalletTradeLimitUseCase
import cn.junmov.mirror.wallet.domain.FlowWalletUseCase

class WalletDetailViewModel @ViewModelInject constructor(
    private val flowWalletTradeLimit: FlowWalletTradeLimitUseCase,
    private val flowWallet: FlowWalletUseCase
) : ViewModel() {

    private val _walletId = MutableLiveData<Long>()

    val wallet: LiveData<Wallet> = _walletId.switchMap { flowWallet(it).asLiveData() }

    val vouchers: LiveData<List<ItemVoucher>> = _walletId.switchMap {
        flowWalletTradeLimit(it, 10).asLiveData()
    }

    val balance: LiveData<String> = wallet.map { MoneyUtils.centToYuan(it.balance()) }
    val createAt: LiveData<String> =
        wallet.map { TimeUtils.dateToString(it.account.createAt.toLocalDate()) }

    val message = MutableLiveData<Int>()

    fun loadData(id: Long) {
        _walletId.value = id
    }

}