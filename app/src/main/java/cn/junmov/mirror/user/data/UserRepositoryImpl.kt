package cn.junmov.mirror.user.data

import cn.junmov.mirror.core.data.remote.API_AUTH
import cn.junmov.mirror.core.data.remote.API_HTTP
import cn.junmov.mirror.core.data.remote.MirrorService
import cn.junmov.mirror.core.data.store.KEY_USER_NICK_NAME
import cn.junmov.mirror.core.data.store.KEY_USER_SIGNED
import cn.junmov.mirror.core.data.store.KEY_USER_USERNAME
import cn.junmov.mirror.core.data.store.ProfileDataStore
import kotlinx.coroutines.flow.Flow

class UserRepositoryImpl(
    private val dataStore: ProfileDataStore, private val service: MirrorService
) : UserRepository {
    override suspend fun signIn(username: String, password: String, ip: String) {
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

    override suspend fun signOut() {
        dataStore.writeBoolean(KEY_USER_SIGNED, false)
    }

    override suspend fun createGuest() {
        dataStore.writeString(KEY_USER_NICK_NAME, "游客用户")
        dataStore.writeBoolean(KEY_USER_SIGNED, true)
    }

    override fun flowUserName(): Flow<String> = dataStore.flowString(KEY_USER_USERNAME, "")

    override fun flowUserNickName(): Flow<String> = dataStore.flowString(KEY_USER_NICK_NAME, "")

    override fun flowSign(): Flow<Boolean> = dataStore.flowBoolean(KEY_USER_SIGNED, false)

}