package cn.junmov.mirror.sync.data.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.preferencesKey
import androidx.datastore.preferences.createDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 * 键值对数据存储仓库，应包含：
 * 1. app_id 用户刷新令牌，有效期为半年，登录时服务器颁发，登出时清除
 * 2. user_stat 用户状态，即登录和未登录
 * 3. sync_at 上次同步时间，暂时以手机系统时间为准
 */
class ProfileDataStore(private val ctx: Context) {

    private val dataStore: DataStore<Preferences> = ctx.createDataStore(name = "profiles")

    fun flowString(key: String): Flow<String> =
        dataStore.data.map { pref -> pref[preferencesKey(key)] ?: "2010-07-01 00:00:00" }

    suspend fun writeString(key: String, value: String) {
        dataStore.edit { pref -> pref[preferencesKey(key)] = value }
    }

}
