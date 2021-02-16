package cn.junmov.mirror.core.utility

import cn.junmov.mirror.core.data.db.entity.Split
import cn.junmov.mirror.core.data.db.entity.Voucher
import cn.junmov.mirror.core.data.model.AccountType
import cn.junmov.mirror.core.data.model.VoucherAndSplit
import cn.junmov.mirror.core.data.model.VoucherType
import java.time.LocalDate
import java.time.LocalDateTime

object DebtVoucherFactory {

    fun createVoucher(date: LocalDate, capital: Int, interest: Int): VoucherAndSplit {
        val now = LocalDateTime.now()
        val ids = SnowFlakeUtil.genIds(3)
        val voucher = getVoucher(ids[0], now, date, capital + interest)
        val capitalSplit = getCapitalSplit(ids[0], ids[1], now, date, capital)
        val interestSplit = getInterestSplit(ids[0], ids[2], now, interest)
        val splits = mutableListOf<Split>()
        if (capitalSplit != null) splits.add(capitalSplit)
        if (interestSplit != null) splits.add(interestSplit)
        return VoucherAndSplit(voucher = voucher, splits = splits)
    }

    private fun getInterestSplit(
        voucherId: Long, id: Long, now: LocalDateTime, interest: Int
    ): Split? {
        if (interest == 0) return null
        return Split(
            id = id, voucherId = voucherId, amount = interest, debit = true,
            accountId = AccountEnum.INTEREST.id,
            accountType = AccountEnum.INTEREST.type,
            accountName = AccountEnum.INTEREST.fullName,
            accountParentId = AccountEnum.QI_TA_ZHI_CHU.id,
            createAt = now, modifiedAt = now, deleted = false
        )
    }

    private fun getCapitalSplit(
        voucherId: Long, id: Long, now: LocalDateTime, date: LocalDate, capital: Int
    ): Split? {
        if (capital == 0) return null
        val account = when (date.dayOfMonth) {
            4 -> AccountEnum.JIE_BEI
            9 -> AccountEnum.HUA_BEI
            26 -> AccountEnum.WANG_SHANG_DAI
            else -> AccountEnum.NONE
        }
        return Split(
            id = id, voucherId = voucherId, amount = capital, debit = true,
            accountId = account.id, accountType = AccountType.PAYABLE,
            accountName = account.fullName, accountParentId = 0L, createAt = now,
            modifiedAt = now, deleted = false
        )
    }

    private fun getVoucher(id: Long, time: LocalDateTime, date: LocalDate, amount: Int): Voucher {
        val summary = when (date.dayOfMonth) {
            4 -> "还款-借呗还款${MoneyUtils.centToYuan(amount)}元"
            9 -> "还款-花呗还款${MoneyUtils.centToYuan(amount)}元"
            26 -> "还款-网商贷还款${MoneyUtils.centToYuan(amount)}元"
            else -> "错误数据"
        }
        return Voucher(
            id = id, summary = summary, dateAt = time.toLocalDate(), timeAt = time.toLocalTime(),
            thingId = ThingEnum.DEBT.id, thingName = ThingEnum.DEBT.thingName,
            audited = false, type = VoucherType.EXPEND, profit = 0,
            createAt = time, modifiedAt = time, deleted = false
        )
    }

}