package cn.junmov.mirror.sync.data

import cn.junmov.mirror.core.data.db.dao.MineDao
import cn.junmov.mirror.core.data.remote.API_HTTP
import cn.junmov.mirror.core.data.remote.API_TABLE
import cn.junmov.mirror.core.data.remote.MirrorService
import cn.junmov.mirror.core.data.store.*
import cn.junmov.mirror.core.utility.TimeUtils
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
    fun flowSyncAt(key: String): Flow<String> = cache.flowString(key, DEFAULT_VALUE_SYNC_AT)

    suspend fun pull(ipAddress: String): String {
        val url = "$API_HTTP$ipAddress$API_TABLE"
        val cacheKey = KEY_SYNC_AT
        val lastSync = cache.flowString(cacheKey, DEFAULT_VALUE_SYNC_AT).first()
        return try {
            val respond = service.pull(url, lastSync)
            if (respond.isOk()) {
                val data = respond.data
                saveToDb(data)
                cache.writeString(cacheKey, TimeUtils.dateTimeToString(LocalDateTime.now()))
                "拉取成功"
            } else {
                respond.message
            }
        } catch (e: Exception) {
            "网络错误"
        }
    }

    private suspend fun saveToDb(data: TableData) {
        withContext(Dispatchers.IO) {
            if (data.accounts.isNotEmpty()) dao.insertAccount(data.accounts)
            if (data.assets.isNotEmpty()) dao.insertAsset(data.assets)
            if (data.assetLogs.isNotEmpty()) dao.insertAssetLog(data.assetLogs)
            if (data.debts.isNotEmpty()) dao.insertDebt(data.debts)
            if (data.repays.isNotEmpty()) dao.insertRepay(data.repays)
            if (data.things.isNotEmpty()) dao.insertThing(data.things)
            if (data.todos.isNotEmpty()) dao.insertTodo(data.todos)
            if (data.splits.isNotEmpty()) dao.insertSplits(data.splits)
            if (data.vouchers.isNotEmpty()) dao.insertVoucher(data.vouchers)
        }
    }

    suspend fun push(ipAddress: String): String {
        val url = "$API_HTTP$ipAddress$API_TABLE"
        val syncKey = KEY_SYNC_AT
        val lastSync = cache.flowString(syncKey, DEFAULT_VALUE_SYNC_AT).first()
        return try {
            val tableData = dbSource(TimeUtils.stringToDateTime(lastSync))
            val respond = service.push(url, tableData)
            if (respond.isOk()) {
                val data = respond.data
                if (data.isNotEmpty()) {
                    dao.insertAccount(data)
                }
                cache.writeString(syncKey, TimeUtils.dateTimeToString(LocalDateTime.now()))
                "推送成功"
            } else {
                respond.message
            }
        } catch (e: Exception) {
            "网络错误"
        }
    }

    private suspend fun dbSource(lastSync: LocalDateTime): TableData {
            val accounts = dao.listAccount(lastSync)
            val assets = dao.listAsset(lastSync)
            val assetLogs = dao.listAssetLog(lastSync)
            val debts = dao.listDebt(lastSync)
            val repays = dao.listRepay(lastSync)
            val splits = dao.listSplit(lastSync)
            val things = dao.listThing(lastSync)
            val todos = dao.listTodo(lastSync)
            val vouchers = dao.listVoucher(lastSync)
            return TableData(
                accounts = accounts, assets = assets, assetLogs = assetLogs,
                debts = debts, repays = repays, todos = todos,
                things = things, splits = splits, vouchers = vouchers
            )
    }

}
