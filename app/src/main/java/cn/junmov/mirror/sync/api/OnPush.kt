package cn.junmov.mirror.sync.api

import cn.junmov.mirror.core.data.remote.HttpRespond
import cn.junmov.mirror.core.data.store.ProfileDataStore
import cn.junmov.mirror.core.utility.TimeUtils
import kotlinx.coroutines.flow.first
import java.time.LocalDateTime

abstract class OnPush<T> {

    protected abstract fun apiUrl(): String

    protected abstract fun cacheKey(): String

    protected abstract suspend fun dbSource(lastSync: LocalDateTime): List<T>

    protected abstract suspend fun apiCall(url: String, list: List<T>): HttpRespond<String>

    protected open fun msgSuccess(): String = "推送成功"

    suspend fun process(cache: ProfileDataStore): String {
        val key = cacheKey()
        val lastSync = cache.flowString(key, "2010-07-01 00:00:00").first()
        val list = dbSource(TimeUtils.stringToDateTime(lastSync))
        val url = apiUrl()
        return try {
            val respond = apiCall(url, list)
            if (respond.isOk()) {
                val now = TimeUtils.dateTimeToString(LocalDateTime.now())
                cache.writeString(key, now)
                msgSuccess()
            } else {
                respond.message
            }
        } catch (e: Exception) {
            "网络错误"
        }
    }

}