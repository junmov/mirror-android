package cn.junmov.mirror.sync.api

import android.util.Log
import cn.junmov.mirror.core.utility.TimeUtils
import cn.junmov.mirror.core.data.remote.HttpRespond
import cn.junmov.mirror.core.data.store.ProfileDataStore
import kotlinx.coroutines.flow.first
import java.time.LocalDateTime

abstract class OnPull<T> {
    protected abstract fun apiUrl(): String
    protected abstract fun cacheKey(): String
    protected abstract suspend fun apiCall(url: String, t: String): HttpRespond<List<T>>
    protected abstract suspend fun saveToDb(list: List<T>)

    protected open fun msgSuccess(): String = "拉取成功"

    suspend fun process(cache: ProfileDataStore): String {
        val key = cacheKey()
        val lastSync = cache.flowString(key, "2010-07-01 00:00:00").first()
        val url = apiUrl()
        return try {
            val respond = apiCall(apiUrl(), lastSync)
            if (respond.isOk()) {
                val data = respond.data
                Log.i("onPull", data[0].toString())
                saveToDb(data)
                cache.writeString(key, TimeUtils.dateTimeToString(LocalDateTime.now()))
                msgSuccess()
            } else {
                respond.message
            }
        } catch (e: Exception) {
            "网络错误"
        }
    }
}