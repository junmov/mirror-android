package cn.junmov.mirror.mine.data

import cn.junmov.mirror.core.data.entity.*
import cn.junmov.mirror.mine.api.*
import cn.junmov.mirror.mine.data.local.*
import kotlinx.coroutines.flow.Flow
import java.time.LocalDateTime

class MineRepository(
    private val cache: ProfileDataStore,
    private val service: MirrorService,
    private val dao: MineDao
) {
    fun flowSyncAt(key: String): Flow<String> = cache.flowString(key)

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

    suspend fun pushRepay(ip: String): String {
        return object : OnPush<Repay>() {
            override fun apiUrl(): String = "$API_HTTP$ip$API_REPAY"

            override fun cacheKey(): String = KEY_SYNC_AT_REPAY

            override suspend fun dbSource(lastSync: LocalDateTime): List<Repay> =
                dao.listRepay(lastSync)

            override suspend fun apiCall(url: String, list: List<Repay>): HttpRespond<String> =
                service.pushRepay(url,list)
        }.process(cache)
    }

}
