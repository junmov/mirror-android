package cn.junmov.mirror.voucher.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import cn.junmov.mirror.R
import cn.junmov.mirror.account.domain.FlowAllAccountByLeafUseCase
import cn.junmov.mirror.core.data.AccountType
import cn.junmov.mirror.core.data.entity.Account
import cn.junmov.mirror.core.data.entity.Split
import cn.junmov.mirror.core.data.entity.Thing
import cn.junmov.mirror.core.data.entity.Voucher
import cn.junmov.mirror.core.utility.MoneyUtils
import cn.junmov.mirror.core.utility.SnowFlakeUtil
import cn.junmov.mirror.core.utility.TimeUtils
import cn.junmov.mirror.thing.domain.FlowAllThingUseCase
import cn.junmov.mirror.voucher.domain.AuditVoucherUseCase
import kotlinx.coroutines.launch
import java.time.LocalDateTime

class VoucherFormViewModel @ViewModelInject constructor(
    private val auditVoucher: AuditVoucherUseCase,
    private val flowAllAccount: FlowAllAccountByLeafUseCase,
    private val flowAllThing: FlowAllThingUseCase
) : ViewModel() {

    val things: LiveData<List<Thing>> = flowAllThing().asLiveData()
    val accounts: LiveData<List<Account>> = flowAllAccount(true).asLiveData()

    val inputVoucher = MutableLiveData<Voucher>()
    val inputSplit = MutableLiveData<Split>()
    val inputOccurAt = MutableLiveData<String>()
    val inputSplitAmount = MutableLiveData<String>()
    val inputSplits = MutableLiveData<List<Split>>()

    private val _totalDebit = MutableLiveData(0)
    private val _totalCredit = MutableLiveData(0)
    val totalDebit: LiveData<String> = _totalDebit.map { MoneyUtils.centToYuan(it) }
    val totalCredit: LiveData<String> = _totalCredit.map { MoneyUtils.centToYuan(it) }

    val updated = MutableLiveData<Boolean>()
    val message = MutableLiveData<Int>()

    init {
        val ids = SnowFlakeUtil.genIds(2)
        val now = LocalDateTime.now()
        inputVoucher.value = Voucher(
            id = ids[0], summary = "", dateAt = now.toLocalDate(),
            timeAt = now.toLocalTime(),
            thingId = 0, thingName = "", profit = 0,
            createAt = now, modifiedAt = now
        )
        inputSplit.value = Split(
            id = ids[1], voucherId = ids[0], amount = 0, isDebit = false,
            accountId = 0, accountName = "", accountType = AccountType.DEBT, accountParentId = 0L,
            createAt = now, modifiedAt = now
        )
        inputOccurAt.value = TimeUtils.dateTimeToString(now)
        inputSplits.value = emptyList()
    }

    fun selectThing(thing: Thing) {
        inputVoucher.value?.thingId = thing.id
        inputVoucher.value?.thingName = thing.name
    }

    fun selectAccount(account: Account) {
        inputSplit.value?.accountId = account.id
        inputSplit.value?.accountName = account.fullName
        inputSplit.value?.accountType = account.type
        inputSplit.value?.accountParentId = account.parentId
    }

    fun submitSplit() {
        val oldSplit = inputSplit.value ?: return
        val yuan = inputSplitAmount.value ?: return
        if (!MoneyUtils.isFormat(yuan)) {
            message.value = R.string.error_balance_not_format
            return
        }
        val cent = MoneyUtils.yuanToCent(yuan)
        oldSplit.amount = cent
        if (oldSplit.isDebit) {
            _totalDebit.value = _totalDebit.value?.plus(cent)
        } else {
            _totalCredit.value = _totalCredit.value?.plus(cent)
        }
        val emptySplit = Split(
            SnowFlakeUtil.genId(), inputVoucher.value!!.id, 0, false, 0, 0, "", AccountType.DEBT
        )
        val oldSplits = inputSplits.value ?: emptyList()
        val splits = oldSplits.plus(oldSplit)
        inputSplits.value = splits
        inputSplit.value = emptySplit
        inputSplitAmount.value = ""
    }

    fun editSplit(split: Split) {
        inputSplit.value = split
        inputSplitAmount.value = MoneyUtils.centToYuan(split.amount)
        removeSplit(split)
    }

    fun removeSplit(split: Split) {
        if (split.isDebit) {
            _totalDebit.value = _totalDebit.value?.minus(split.amount)
        } else {
            _totalCredit.value = _totalCredit.value?.minus(split.amount)
        }
        val oldSplits = inputSplits.value ?: emptyList()
        val splits = oldSplits.minus(split)
        inputSplits.value = splits
    }

    fun audit() {
        val occurAtStr = inputOccurAt.value ?: return
        val debit = _totalDebit.value ?: return
        val credit = _totalCredit.value ?: return
        val splits = inputSplits.value ?: return
        val voucher = inputVoucher.value ?: return
        if (splits.isEmpty()) {
            message.value = R.string.error_no_split
            return
        }
        if (debit == 0 || credit == 0) {
            message.value = R.string.error_debit_credit_reconcile
            return
        }
        if (debit != credit) {
            message.value = R.string.error_debit_credit_inequality
            return
        }
        val occurAt = TimeUtils.stringToDateTime(occurAtStr)
        voucher.dateAt = occurAt.toLocalDate()
        voucher.timeAt = occurAt.toLocalTime()
        viewModelScope.launch {
            auditVoucher(voucher, splits)
            updated.value = true
        }

    }
}