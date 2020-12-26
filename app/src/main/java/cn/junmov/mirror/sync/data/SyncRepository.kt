package cn.junmov.mirror.sync.data

import cn.junmov.mirror.core.data.db.dao.MineDao
import cn.junmov.mirror.core.data.db.entity.*
import cn.junmov.mirror.core.data.remote.*
import cn.junmov.mirror.core.data.store.*
import cn.junmov.mirror.sync.api.OnPull
import cn.junmov.mirror.sync.api.OnPush
import kotlinx.coroutines.flow.Flow
import java.time.LocalDateTime

class SyncRepository(
    private val cache: ProfileDataStore,
    private val service: MirrorService,
    private val dao: MineDao
) {
    fun flowSyncAt(key: String): Flow<String> = cache.flowString(key, "2010-07-01 00:00:00")

    suspend fun pushAccount(ipAddress: String): String {
        return object : OnPush<Account>() {
            override fun apiUrl(): String = "$API_HTTP$ipAddress$API_ACCOUNT"

            override fun cacheKey(): String = KEY_SYNC_AT_ACCOUNT

            override suspend fun dbSource(lastSync: LocalDateTime): List<Account> =
                dao.listAccount(lastSync)

            override suspend fun apiCall(url: String, list: List<Account>): HttpRespond<String> =
                service.pushAccount(url, list)
        }.process(cache)
    }

    suspend fun pullAccount(ipAddress: String): String {
        return object : OnPull<Account>() {
            override fun apiUrl(): String = "$API_HTTP$ipAddress$API_ACCOUNT"

            override fun cacheKey(): String = KEY_SYNC_AT_ACCOUNT

            override suspend fun apiCall(url: String, t: String): HttpRespond<List<Account>> =
                service.pullAccount(url, t)

            override suspend fun saveToDb(list: List<Account>) =
                dao.insertAccount(list)
        }.process(cache)
    }

    suspend fun pushBalance(ipAddress: String): String {
        return object : OnPush<Balance>() {
            override fun apiUrl(): String = "$API_HTTP$ipAddress$API_BALANCE"

            override fun cacheKey(): String = KEY_SYNC_AT_BALANCE

            override suspend fun dbSource(lastSync: LocalDateTime): List<Balance> =
                dao.listBalance(lastSync)

            override suspend fun apiCall(url: String, list: List<Balance>): HttpRespond<String> =
                service.pushBalance(url, list)
        }.process(cache)
    }

    suspend fun pullBalance(ipAddress: String): String {
        return object : OnPull<Balance>() {
            override fun apiUrl(): String = "$API_HTTP$ipAddress$API_BALANCE"

            override fun cacheKey(): String = KEY_SYNC_AT_BALANCE

            override suspend fun apiCall(url: String, t: String): HttpRespond<List<Balance>> =
                service.pullBalance(url, t)

            override suspend fun saveToDb(list: List<Balance>) =
                dao.insertBalance(list)
        }.process(cache)
    }

    suspend fun pushTrade(ipAddress: String): String {
        return object : OnPush<Trade>() {
            override fun apiUrl(): String = "$API_HTTP$ipAddress$API_TRADE"

            override fun cacheKey(): String = KEY_SYNC_AT_TRADE

            override suspend fun dbSource(lastSync: LocalDateTime): List<Trade> =
                dao.listTrade(lastSync)

            override suspend fun apiCall(url: String, list: List<Trade>): HttpRespond<String> =
                service.pushTrade(url, list)
        }.process(cache)
    }

    suspend fun pullTrade(ipAddress: String): String {
        return object : OnPull<Trade>() {
            override fun apiUrl(): String = "$API_HTTP$ipAddress$API_TRADE"

            override fun cacheKey(): String = KEY_SYNC_AT_TRADE

            override suspend fun apiCall(url: String, t: String): HttpRespond<List<Trade>> =
                service.pullTrade(url, t)

            override suspend fun saveToDb(list: List<Trade>) =
                dao.insertTrade(list)
        }.process(cache)
    }

    suspend fun pushSplit(ipAddress: String): String {
        return object : OnPush<Split>() {
            override fun apiUrl(): String = "$API_HTTP$ipAddress$API_SPLIT"

            override fun cacheKey(): String = KEY_SYNC_AT_SPLIT

            override suspend fun dbSource(lastSync: LocalDateTime): List<Split> =
                dao.listSplit(lastSync)

            override suspend fun apiCall(url: String, list: List<Split>): HttpRespond<String> =
                service.pushSplit(url, list)
        }.process(cache)
    }

    suspend fun pullSplit(ipAddress: String): String {
        return object : OnPull<Split>() {
            override fun apiUrl(): String = "$API_HTTP$ipAddress$API_SPLIT"

            override fun cacheKey(): String = KEY_SYNC_AT_SPLIT

            override suspend fun apiCall(url: String, t: String): HttpRespond<List<Split>> =
                service.pullSplit(url, t)

            override suspend fun saveToDb(list: List<Split>) =
                dao.insertSplits(list)
        }.process(cache)
    }

    suspend fun pushVoucher(ipAddress: String): String {
        return object : OnPush<Voucher>() {
            override fun apiUrl(): String = "$API_HTTP$ipAddress$API_VOUCHER"

            override fun cacheKey(): String = KEY_SYNC_AT_VOUCHER

            override suspend fun dbSource(lastSync: LocalDateTime): List<Voucher> =
                dao.listVoucher(lastSync)

            override suspend fun apiCall(url: String, list: List<Voucher>): HttpRespond<String> =
                service.pushVoucher(url, list)
        }.process(cache)
    }

    suspend fun pullVoucher(ipAddress: String): String {
        return object : OnPull<Voucher>() {
            override fun apiUrl(): String = "$API_HTTP$ipAddress$API_VOUCHER"

            override fun cacheKey(): String = KEY_SYNC_AT_VOUCHER

            override suspend fun apiCall(url: String, t: String): HttpRespond<List<Voucher>> =
                service.pullVoucher(url, t)

            override suspend fun saveToDb(list: List<Voucher>) =
                dao.insertVoucher(list)
        }.process(cache)
    }

    suspend fun pushTodo(ipAddress: String): String {
        return object : OnPush<Todo>() {
            override fun apiUrl(): String = "$API_HTTP$ipAddress$API_TODO"

            override fun cacheKey(): String = KEY_SYNC_AT_TODO

            override suspend fun dbSource(lastSync: LocalDateTime): List<Todo> =
                dao.listTodo(lastSync)

            override suspend fun apiCall(url: String, list: List<Todo>): HttpRespond<String> =
                service.pushTodo(url, list)
        }.process(cache)
    }

    suspend fun pullTodo(ipAddress: String): String {
        return object : OnPull<Todo>() {
            override fun apiUrl(): String = "$API_HTTP$ipAddress$API_TODO"

            override fun cacheKey(): String = KEY_SYNC_AT_TODO

            override suspend fun apiCall(url: String, t: String): HttpRespond<List<Todo>> =
                service.pullTodo(url, t)

            override suspend fun saveToDb(list: List<Todo>) =
                dao.insertTodo(list)
        }.process(cache)
    }

    suspend fun pushThing(ipAddress: String): String {
        return object : OnPush<Thing>() {
            override fun apiUrl(): String = "$API_HTTP$ipAddress$API_THING"

            override fun cacheKey(): String = KEY_SYNC_AT_THING

            override suspend fun dbSource(lastSync: LocalDateTime): List<Thing> =
                dao.listThing(lastSync)

            override suspend fun apiCall(url: String, list: List<Thing>): HttpRespond<String> =
                service.pushThing(url, list)
        }.process(cache)
    }

    suspend fun pullThing(ipAddress: String): String {
        return object : OnPull<Thing>() {
            override fun apiUrl(): String = "$API_HTTP$ipAddress$API_THING"

            override fun cacheKey(): String = KEY_SYNC_AT_THING

            override suspend fun apiCall(url: String, t: String): HttpRespond<List<Thing>> =
                service.pullThing(url, t)

            override suspend fun saveToDb(list: List<Thing>) =
                dao.insertThing(list)
        }.process(cache)
    }

    suspend fun pushDebt(ipAddress: String): String {
        return object : OnPush<Debt>() {
            override fun apiUrl(): String = "$API_HTTP$ipAddress$API_DEBT"

            override fun cacheKey(): String = KEY_SYNC_AT_DEBT

            override suspend fun dbSource(lastSync: LocalDateTime): List<Debt> =
                dao.listDebt(lastSync)

            override suspend fun apiCall(url: String, list: List<Debt>): HttpRespond<String> =
                service.pushDebt(url, list)
        }.process(cache)
    }

    suspend fun pullDebt(ipAddress: String): String {
        return object : OnPull<Debt>() {
            override fun apiUrl(): String = "$API_HTTP$ipAddress$API_DEBT"

            override fun cacheKey(): String = KEY_SYNC_AT_DEBT

            override suspend fun apiCall(url: String, t: String): HttpRespond<List<Debt>> =
                service.pullDebt(url, t)

            override suspend fun saveToDb(list: List<Debt>) =
                dao.insertDebt(list)
        }.process(cache)
    }

    suspend fun pushAsset(ip: String): String {
        return object : OnPush<Asset>() {
            override fun apiUrl(): String = "$API_HTTP$ip$API_ASSET"

            override fun cacheKey(): String = KEY_SYNC_AT_ASSET

            override suspend fun dbSource(lastSync: LocalDateTime): List<Asset> =
                dao.listAsset(lastSync)

            override suspend fun apiCall(url: String, list: List<Asset>): HttpRespond<String> =
                service.pushAsset(url, list)
        }.process(cache)
    }

    suspend fun pullAsset(ipAddress: String): String {
        return object : OnPull<Asset>() {
            override fun apiUrl(): String = "$API_HTTP$ipAddress$API_ASSET"

            override fun cacheKey(): String = KEY_SYNC_AT_ASSET

            override suspend fun apiCall(url: String, t: String): HttpRespond<List<Asset>> =
                service.pullAsset(url, t)

            override suspend fun saveToDb(list: List<Asset>) =
                dao.insertAsset(list)
        }.process(cache)
    }

    suspend fun pushAssetLog(ip: String): String {
        return object : OnPush<AssetLog>() {
            override fun apiUrl(): String = "$API_HTTP$ip$API_ASSET_LOG"

            override fun cacheKey(): String = KEY_SYNC_AT_ASSET_LOG

            override suspend fun dbSource(lastSync: LocalDateTime): List<AssetLog> =
                dao.listAssetLog(lastSync)

            override suspend fun apiCall(url: String, list: List<AssetLog>): HttpRespond<String> =
                service.pushAssetLog(url, list)
        }.process(cache)
    }

    suspend fun pullAssetLog(ipAddress: String): String {
        return object : OnPull<AssetLog>() {
            override fun apiUrl(): String = "$API_HTTP$ipAddress$API_ASSET_LOG"

            override fun cacheKey(): String = KEY_SYNC_AT_ASSET_LOG

            override suspend fun apiCall(url: String, t: String): HttpRespond<List<AssetLog>> =
                service.pullAssetLog(url, t)

            override suspend fun saveToDb(list: List<AssetLog>) =
                dao.insertAssetLog(list)
        }.process(cache)
    }

    suspend fun pushBill(ip: String): String {
        return object : OnPush<Bill>() {
            override fun apiUrl(): String = "$API_HTTP$ip$API_BILL"

            override fun cacheKey(): String = KEY_SYNC_AT_BILL

            override suspend fun dbSource(lastSync: LocalDateTime): List<Bill> =
                dao.listBill(lastSync)

            override suspend fun apiCall(url: String, list: List<Bill>): HttpRespond<String> =
                service.pushBill(url, list)
        }.process(cache)
    }

    suspend fun pullBill(ipAddress: String): String {
        return object : OnPull<Bill>() {
            override fun apiUrl(): String = "$API_HTTP$ipAddress$API_BILL"

            override fun cacheKey(): String = KEY_SYNC_AT_BILL

            override suspend fun apiCall(url: String, t: String): HttpRespond<List<Bill>> =
                service.pullBill(url, t)

            override suspend fun saveToDb(list: List<Bill>) =
                dao.insertBill(list)
        }.process(cache)
    }

    suspend fun pushRepay(ip: String): String {
        return object : OnPush<Repay>() {
            override fun apiUrl(): String = "$API_HTTP$ip$API_REPAY"

            override fun cacheKey(): String = KEY_SYNC_AT_REPAY

            override suspend fun dbSource(lastSync: LocalDateTime): List<Repay> =
                dao.listRepay(lastSync)

            override suspend fun apiCall(url: String, list: List<Repay>): HttpRespond<String> =
                service.pushRepay(url, list)
        }.process(cache)
    }

    suspend fun pullRepay(ipAddress: String): String {
        return object : OnPull<Repay>() {
            override fun apiUrl(): String = "$API_HTTP$ipAddress$API_REPAY"

            override fun cacheKey(): String = KEY_SYNC_AT_REPAY

            override suspend fun apiCall(url: String, t: String): HttpRespond<List<Repay>> =
                service.pullRepay(url, t)

            override suspend fun saveToDb(list: List<Repay>) =
                dao.insertRepay(list)
        }.process(cache)
    }

}
