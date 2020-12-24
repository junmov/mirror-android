package cn.junmov.mirror.wallet.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import cn.junmov.mirror.core.data.db.entity.Account
import cn.junmov.mirror.wallet.domain.FlowAllWalletUseCase

class WalletViewModel @ViewModelInject constructor(
    private val flowAllWallet: FlowAllWalletUseCase
) : ViewModel() {
    val wallets: LiveData<List<Account>> = flowAllWallet().asLiveData()
}