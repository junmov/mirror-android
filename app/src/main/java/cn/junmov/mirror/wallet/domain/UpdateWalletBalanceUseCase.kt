package cn.junmov.mirror.wallet.domain

import cn.junmov.mirror.core.data.db.dao.BalanceDao
import cn.junmov.mirror.core.data.db.entity.Account
import java.time.LocalDateTime

class UpdateWalletBalanceUseCase(private val balanceDao: BalanceDao) {
    suspend operator fun invoke(account: Account, newBalance: Int) {
        val now = LocalDateTime.now()
        val balances = balanceDao.findAllByWallet(account.id)
        val delta = newBalance - account.base
        balances.forEach {
            it.modifiedAt = now
            it.base += delta
        }
        account.modifiedAt = now
        account.base = newBalance
        balanceDao.updateBalanceTransaction(account, balances)
    }
}