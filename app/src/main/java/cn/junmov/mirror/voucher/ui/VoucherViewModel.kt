package cn.junmov.mirror.voucher.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import cn.junmov.mirror.core.data.db.entity.Account
import cn.junmov.mirror.core.data.db.entity.Voucher
import cn.junmov.mirror.core.utility.MoneyUtils
import cn.junmov.mirror.core.utility.SnowFlakeUtil
import cn.junmov.mirror.core.utility.TimeUtils
import cn.junmov.mirror.voucher.domain.*
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

class VoucherViewModel @ViewModelInject constructor(
    private val createVoucher: CreateVoucherUseCase,
    private val flowAllAccount: FlowAllAccountTradeCountDescUseCase,
) : ViewModel() {

    val accounts: LiveData<List<Account>> = flowAllAccount().asLiveData()

    val voucher = MutableLiveData<Voucher>()

    val inputAmount = MutableLiveData<String>()
    val inputDateAt = MutableLiveData<String>()
    val inputTimeAt = MutableLiveData<String>()

    val updated = MutableLiveData<Boolean>()

    init {
        val now = LocalDateTime.now()
        voucher.value = Voucher(
            id = SnowFlakeUtil.genId(), summary = "", amount = 0,
            dateAt = now.toLocalDate(), timeAt = now.toLocalTime(),
            createAt = now, modifiedAt = now, deleted = false
        )
        inputDateAt.value = TimeUtils.dateToString(now.toLocalDate())
        inputTimeAt.value = TimeUtils.timeToString(now.toLocalTime())
    }

    fun selectDebit(debit: Account) {
        voucher.value?.debitId = debit.id
        voucher.value?.debitName = debit.name
    }

    fun selectCredit(credit: Account) {
        voucher.value?.creditId = credit.id
        voucher.value?.creditName = credit.name
    }

    fun selectDate(date: LocalDate) {
        voucher.value?.dateAt = date
        inputDateAt.value = TimeUtils.dateToString(date)
    }

    fun selectTime(time: LocalTime) {
        voucher.value?.timeAt = time
        inputTimeAt.value = TimeUtils.timeToString(time)
    }

    fun selectThing(thing: String) {
        voucher.value?.thing = thing
    }

    fun submitVoucher() {
        val currentAmount = inputAmount.value ?: return
        val currentVoucher = voucher.value ?: return
        currentVoucher.amount = MoneyUtils.yuanToCent(currentAmount)
        viewModelScope.launch {
            createVoucher(currentVoucher)
            updated.value = true
        }
    }

}