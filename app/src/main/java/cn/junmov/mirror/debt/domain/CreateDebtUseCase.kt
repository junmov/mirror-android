package cn.junmov.mirror.debt.domain

import cn.junmov.mirror.core.data.db.dao.AccountDao
import cn.junmov.mirror.core.data.db.dao.DebtDao
import cn.junmov.mirror.core.data.db.entity.Account
import cn.junmov.mirror.core.data.db.entity.Debt
import cn.junmov.mirror.core.data.db.entity.Repay
import cn.junmov.mirror.core.data.db.entity.Voucher
import cn.junmov.mirror.core.utility.MoneyUtils
import cn.junmov.mirror.core.utility.SnowFlakeUtil
import cn.junmov.mirror.core.utility.Things
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

class CreateDebtUseCase(private val dao: DebtDao, private val accountDao: AccountDao) {
    suspend operator fun invoke(
        debt: Debt, fund: Account?, startAt: LocalDate, count: Int
    ) {
        val now = LocalDateTime.now()

        val repays = mutableListOf<Repay>()
        var voucher: Voucher? = null
        val accounts = mutableListOf<Account>()

        val amountAvg: Int = debt.capital.div(count)

        val ids = SnowFlakeUtil.genIds(count + 1)
        for (i in 1..count) {
            val dateAt = startAt.plusMonths(i - 1L)
            val item = Repay(
                id = ids[i], debtId = debt.id, indexOfDebt = "${i + 1}/$count",
                borrowId = debt.borrowId, borrowName = debt.borrowName,
                dateAt = dateAt, capital = amountAvg, interest = 0, repaid = false,
                createAt = now, modifiedAt = now, deleted = false
            )
            repays.add(item)
        }

        if (fund != null) {
            val payable = accountDao.findById(debt.borrowId)
            voucher = Voucher(
                id= ids[0], dateAt = debt.borrowAt, timeAt = LocalTime.MIDNIGHT,
                summary = "${payable.name}分期${MoneyUtils.centToYuan(debt.capital)}元",
                amount = debt.capital, thing = Things.DEBT,
                debitId = fund.id, debitName = fund.name,
                creditId = payable.id, creditName = payable.name,
                createAt = now, modifiedAt = now, deleted = false
            )
            payable.plusAmount(false, debt.capital)
            payable.modifiedAt = now
            fund.plusAmount(true, debt.capital)
            fund.modifiedAt = now
            accounts.add(payable)
            accounts.add(fund)
        }
        withContext(Dispatchers.IO) {
            dao.createAgingDebtTransaction(debt, repays, voucher, accounts)
        }
    }

}
