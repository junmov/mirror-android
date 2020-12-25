package cn.junmov.mirror.user.data

import kotlinx.coroutines.flow.Flow

interface UserRepository {
    suspend fun signIn(username: String, password: String, ip: String)

    suspend fun signOut()

    suspend fun createGuest()

    fun flowUserName(): Flow<String>

    fun flowUserNickName(): Flow<String>

    fun flowSign(): Flow<Boolean>
}