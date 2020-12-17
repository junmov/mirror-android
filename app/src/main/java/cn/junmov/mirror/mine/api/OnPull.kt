package cn.junmov.mirror.mine.api

import cn.junmov.mirror.mine.data.local.ProfileDataStore

abstract class OnPull<T> {
    protected abstract fun apiUrl(): String
    protected abstract fun cacheKey(): String
    protected abstract suspend fun apiCall(url: String, header: String)
    protected abstract suspend fun saveToDb()

    protected open fun msgSuccess(): String = "拉取成功"

    protected open fun msgError(): String = "拉取失败"

    suspend fun process(cache: ProfileDataStore): String {
        val key= cacheKey()
        try {
//            apiCall(apiUrl(),)
        } catch (e: Exception) {

        }
        return ""
    }
}