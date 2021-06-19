package cn.junmov.mirror.user.domain

import cn.junmov.mirror.core.data.db.dao.MineDao
import cn.junmov.mirror.core.data.remote.API_HTTP
import cn.junmov.mirror.core.data.remote.API_TABLE
import cn.junmov.mirror.core.data.remote.MirrorService
import cn.junmov.mirror.core.data.store.VALUE_SYNC_AT
import cn.junmov.mirror.core.data.store.KEY_SYNC_AT
import cn.junmov.mirror.core.data.store.ProfileDataStore
import cn.junmov.mirror.core.utility.TimeUtils
import cn.junmov.mirror.core.data.model.TableData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.withContext
import java.time.LocalDateTime

class SyncRepository(
    private val cache: ProfileDataStore,
    private val service: MirrorService,
    private val dao: MineDao
) {
    fun flowSyncAt(key: String): Flow<String> = cache.flowString(key, VALUE_SYNC_AT)

    suspend fun sync(ipAddress: String): String {
        val url = "$API_HTTP$ipAddress$API_TABLE"
        val cacheKey = KEY_SYNC_AT
        val lastSync = cache.flowString(cacheKey, VALUE_SYNC_AT).first()
        try {
            val pullRespond = service.pull(url, lastSync)
            if (!pullRespond.isOk()) return "拉取失败"
            val remote = pullRespond.data
            val local: TableData = dbSource(TimeUtils.stringToDateTime(lastSync))
            val pushRespond = service.push(url, local)
            if (!pushRespond.isOk()) return "推送失败"
            saveToDb(remote)
            cache.writeString(cacheKey, TimeUtils.dateTimeToString(LocalDateTime.now()))
            return "已同步"
        } catch (e: Exception) {
            return "网络错误"
        }
    }

    private suspend fun saveToDb(data: TableData) {
        withContext(Dispatchers.IO) {
            if (data.accounts.isNotEmpty()) dao.insertAccount(data.accounts)
            if (data.assets.isNotEmpty()) dao.insertAsset(data.assets)
            if (data.assetLogs.isNotEmpty()) dao.insertAssetLog(data.assetLogs)
            if (data.debts.isNotEmpty()) dao.insertDebt(data.debts)
            if (data.repays.isNotEmpty()) dao.insertRepay(data.repays)
            if (data.vouchers.isNotEmpty()) dao.insertVoucher(data.vouchers)
        }
    }

    private suspend fun dbSource(lastSync: LocalDateTime): TableData {
        val accounts = dao.listAccount(lastSync)
        val assets = dao.listAsset(lastSync)
        val assetLogs = dao.listAssetLog(lastSync)
        val debts = dao.listDebt(lastSync)
        val repays = dao.listRepay(lastSync)
        val vouchers = dao.listVoucher(lastSync)
        return TableData(
            accounts = accounts, assets = assets, assetLogs = assetLogs,
            debts = debts, repays = repays, vouchers = vouchers
        )
    }

}
