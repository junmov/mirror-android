package cn.junmov.mirror.mine.api

import android.util.Log
import cn.junmov.mirror.core.utility.TimeUtils
import cn.junmov.mirror.mine.data.HttpRespond
import cn.junmov.mirror.mine.data.local.ProfileDataStore
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
        val lastSync = cache.flowString(key).first()
        val list = dbSource(TimeUtils.stringToDateTime(lastSync))
        val url = apiUrl()
        return try {
            val respond = apiCall(url, list)
            Log.i("respond",respond.toString())
            if (respond.isOk()) {
                val now = TimeUtils.dateTimeToString(LocalDateTime.now())
                cache.writeString(key, now)
                msgSuccess()
            } else {
                respond.message
            }
        } catch (e: Exception) {
            Log.e("onPush", "推送到${url}时发生错误", e)
            "网络错误"
        }
    }

}