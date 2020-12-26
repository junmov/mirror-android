package cn.junmov.mirror.core.data.worker

import android.content.Context
import androidx.hilt.Assisted
import androidx.hilt.work.WorkerInject
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import cn.junmov.mirror.core.data.db.MirrorDatabase
import cn.junmov.mirror.core.data.store.KEY_WORK_MONTHLY_BALANCE
import cn.junmov.mirror.core.data.store.ProfileDataStore
import cn.junmov.mirror.core.utility.TimeUtils
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.first
import java.time.LocalDate
import java.time.LocalDateTime

/**
 * 结转上月余额、流入、流出
 */
class AccountBalanceMonthlyWorker @WorkerInject constructor(
    @Assisted appContext: Context, @Assisted params: WorkerParameters,
    private val cache: ProfileDataStore, private val database: MirrorDatabase
) : CoroutineWorker(appContext, params) {

    override suspend fun doWork(): Result = coroutineScope {
        val shouldRun = checkShouldRun()
        if (shouldRun) runTask()
        Result.success()
    }

    private suspend fun runTask() {
        val dao = database.accountDao()
        val accounts = dao.listAll()
        val now = LocalDateTime.now()
        accounts.forEach { it.recycle(now) }
        dao.update(*accounts.toTypedArray())
        cache.writeString(KEY_WORK_MONTHLY_BALANCE, TimeUtils.dateToString(now.toLocalDate()))
    }

    private suspend fun checkShouldRun(): Boolean {
        val lastSnapshotStr = cache.flowString(KEY_WORK_MONTHLY_BALANCE, "2010-01-01").first()
        val lastSnapshot = TimeUtils.stringToDate(lastSnapshotStr)
        val today = LocalDate.now()
        return when {
            lastSnapshot.year < today.year -> true
            lastSnapshot.year == today.year -> lastSnapshot.monthValue < today.monthValue
            else -> false
        }
    }
}