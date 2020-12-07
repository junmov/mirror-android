package cn.junmov.mirror.voucher.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import cn.junmov.mirror.voucher.domain.FlowAllTradAbleAccountUseCase
import cn.junmov.mirror.core.data.AccountType
import cn.junmov.mirror.core.data.entity.Account
import cn.junmov.mirror.core.data.entity.Split
import cn.junmov.mirror.core.utility.MoneyUtils
import cn.junmov.mirror.core.utility.SnowFlakeUtil
import cn.junmov.mirror.voucher.domain.FlowAllSplitByVoucherUseCase
import cn.junmov.mirror.voucher.domain.RemoveSplitUseCase
import cn.junmov.mirror.voucher.domain.SaveSplitUseCase
import kotlinx.coroutines.launch

class SplitFormViewModel @ViewModelInject constructor(
    private val flowAllAccount: FlowAllTradAbleAccountUseCase,
    private val flowAllSplitByVoucher: FlowAllSplitByVoucherUseCase,
    private val saveSplit: SaveSplitUseCase,
    private val removeSplitUseCase: RemoveSplitUseCase
) : ViewModel() {

    val accounts: LiveData<List<Account>> = flowAllAccount().asLiveData()

    private val _voucherId = MutableLiveData<Long>()

    val splits: LiveData<List<Split>> = _voucherId.switchMap {
        flowAllSplitByVoucher(it).asLiveData()
    }

    val totalDebit: LiveData<String> = splits.map { list ->
        val total = list.filter { it.isDebit }.sumBy { it.amount }
        MoneyUtils.centToYuan(total)
    }
    val totalCredit: LiveData<String> = splits.map { list ->
        val total = list.filter { !it.isDebit }.sumBy { it.amount }
        MoneyUtils.centToYuan(total)
    }

    private var _isCreate = false
    val inputSplit = MutableLiveData<Split>()
    val inputSplitAmount = MutableLiveData<String>()

    fun loadData(voucherId: Long) {
        _voucherId.value = voucherId
        initEmptyForm()
    }

    private fun initEmptyForm() {
        val emptySplit = Split(
            SnowFlakeUtil.genId(), _voucherId.value!!, 0, false, 0, 0, "", AccountType.PAYABLE
        )
        inputSplit.value = emptySplit
        inputSplitAmount.value = ""
        _isCreate = true
    }

    fun selectAccount(account: Account) {
        inputSplit.value?.accountId = account.id
        inputSplit.value?.accountName = account.fullName
        inputSplit.value?.accountType = account.type
        inputSplit.value?.accountParentId = account.parentId
    }

    fun submitSplit() {
        val split = inputSplit.value ?: return
        val yuan = inputSplitAmount.value ?: return
        if (!MoneyUtils.isFormat(yuan)) {
            return
        }
        val cent = MoneyUtils.yuanToCent(yuan)
        split.amount = cent
        viewModelScope.launch {
            saveSplit(split, _isCreate)
            initEmptyForm()
        }
    }

    fun editSplit(split: Split) {
        inputSplit.value = split
        inputSplitAmount.value = MoneyUtils.centToYuan(split.amount)
        _isCreate = false
    }

    fun removeSplit(split: Split) {
        viewModelScope.launch {
            inputSplit.value = split
            inputSplitAmount.value = MoneyUtils.centToYuan(split.amount)
            removeSplitUseCase(split)
            initEmptyForm()
        }
    }

}