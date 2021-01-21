package cn.junmov.mirror.core.data.remote

import cn.junmov.mirror.core.data.db.entity.*
import cn.junmov.mirror.core.utility.TimeUtils
import cn.junmov.mirror.sync.data.TableData
import cn.junmov.mirror.user.data.BodySignIn
import com.google.gson.GsonBuilder
import com.google.gson.JsonDeserializer
import com.google.gson.JsonPrimitive
import com.google.gson.JsonSerializer
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime


interface MirrorService {

    @PUT
    suspend fun refreshAuth(@Url url: String, token: String): HttpRespond<String>

    @POST
    suspend fun postAuth(@Url url: String, @Body body: BodySignIn): HttpRespond<String>

    @GET
    suspend fun pull(@Url url: String, @Query("t") syncAt: String): HttpRespond<TableData>

    @POST
    suspend fun push(@Url url: String, @Body data: TableData): HttpRespond<List<Account>>

    companion object {
        private val dateTimeSerializer = JsonSerializer<LocalDateTime> { src, _, _ ->
            JsonPrimitive(TimeUtils.dateTimeToString(src))
        }
        private val dateTimeDeserializer = JsonDeserializer<LocalDateTime> { json, _, _ ->
            TimeUtils.stringToDateTime(json.asJsonPrimitive.asString)
        }

        private val dateSerializer = JsonSerializer<LocalDate> { src, _, _ ->
            JsonPrimitive(TimeUtils.dateToString(src))
        }
        private val dateDeserializer = JsonDeserializer<LocalDate> { json, _, _ ->
            TimeUtils.stringToDate(json.asJsonPrimitive.asString)
        }

        private val timeSerializer = JsonSerializer<LocalTime> { src, _, _ ->
            JsonPrimitive(TimeUtils.timeToString(src))
        }
        private val timeDeserializer = JsonDeserializer<LocalTime> { json, _, _ ->
            TimeUtils.stringToTime(json.asJsonPrimitive.asString)
        }

        fun create(): MirrorService {
            val logger =
                HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BASIC }
            val client = OkHttpClient.Builder().addInterceptor(logger).build()
            val gson = GsonBuilder()
                .registerTypeAdapter(LocalDateTime::class.java, dateTimeSerializer)
                .registerTypeAdapter(LocalDateTime::class.java, dateTimeDeserializer)
                .registerTypeAdapter(LocalDate::class.java, dateSerializer)
                .registerTypeAdapter(LocalDate::class.java, dateDeserializer)
                .registerTypeAdapter(LocalTime::class.java, timeSerializer)
                .registerTypeAdapter(LocalTime::class.java, timeDeserializer)
                .create()
            return Retrofit.Builder()
                .baseUrl("https://www.junmov.cn/")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
                .create(MirrorService::class.java)
        }
    }
}