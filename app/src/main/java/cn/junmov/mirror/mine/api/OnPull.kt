package cn.junmov.mirror.mine.api

import android.util.Log
import cn.junmov.mirror.core.utility.TimeUtils
import cn.junmov.mirror.mine.data.HttpRespond
import cn.junmov.mirror.mine.data.local.ProfileDataStore
import kotlinx.coroutines.flow.first
import java.time.LocalDateTime

abstract class OnPull<T> {
    protected abstract fun apiUrl(): String
    protected abstract fun cacheKey(): String
    protected abstract suspend fun apiCall(url: String): HttpRespond<List<T>>
    protected abstract suspend fun saveToDb(list: List<T>)

    protected open fun msgSuccess(): String = "拉取成功"

    suspend fun process(cache: ProfileDataStore): String {
        val key = cacheKey()
        val lastSync = cache.flowString(key).first()
        val url = "${apiUrl()}?t=$lastSync"
        return try {
            val respond = apiCall(url)
            if (respond.isOk()) {
                saveToDb(respond.data)
                cache.writeString(key, TimeUtils.dateTimeToString(LocalDateTime.now()))
                msgSuccess()
            } else {
                respond.message
            }
        } catch (e: Exception) {
            Log.e("onPull", "从${url}拉取数据发生异常", e)
            "网络错误"
        }
    }
}