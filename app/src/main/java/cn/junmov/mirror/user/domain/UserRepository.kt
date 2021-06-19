package cn.junmov.mirror.user.domain

import cn.junmov.mirror.core.data.remote.API_AUTH
import cn.junmov.mirror.core.data.remote.API_HTTP
import cn.junmov.mirror.core.data.remote.MirrorService
import cn.junmov.mirror.core.data.store.*
import cn.junmov.mirror.core.utility.TimeUtils
import cn.junmov.mirror.user.data.BodySignIn
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate
import java.time.YearMonth

class UserRepository(
    private val dataStore: ProfileDataStore, private val service: MirrorService
) {
    suspend fun signIn(username: String, password: String, ip: String) {
        val url = "$API_HTTP$ip$API_AUTH"
        val body = BodySignIn(username, password)
        try {
            val result = service.postAuth(url, body)
            if (result.isOk()) {
                dataStore.writeString(KEY_USER_USERNAME, username)
                dataStore.writeBoolean(KEY_USER_SIGNED, true)
            }
        } catch (e: Throwable) {
            e.printStackTrace()
        }
    }

    suspend fun signOut() {
        dataStore.writeBoolean(KEY_USER_SIGNED, false)
    }

    suspend fun createGuest() {
        dataStore.writeString(KEY_USER_NICK_NAME, "游客用户")
        dataStore.writeBoolean(KEY_USER_SIGNED, true)
    }

    fun flowUserName(): Flow<String> = dataStore.flowString(KEY_USER_USERNAME, "")

    fun flowUserNickName(): Flow<String> = dataStore.flowString(KEY_USER_NICK_NAME, "")

    fun flowSign(): Flow<Boolean> = dataStore.flowBoolean(KEY_USER_SIGNED, false)

    fun flowBudgetMonth(): Flow<String> {
        return dataStore.flowString(KEY_BUDGET_LAST_MONTH, VALUE_BUDGET_LAST_MONTH)
    }

    suspend fun updateBudgetMonth() {
        dataStore.writeString(
            KEY_BUDGET_LAST_MONTH,
            TimeUtils.dateToString(YearMonth.now().atDay(1))
        )
    }
}