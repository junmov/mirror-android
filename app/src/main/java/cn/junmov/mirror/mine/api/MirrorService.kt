package cn.junmov.mirror.mine.api

import cn.junmov.mirror.core.data.entity.*
import cn.junmov.mirror.core.utility.TimeUtils
import cn.junmov.mirror.interact.data.BodySignIn
import cn.junmov.mirror.mine.data.HttpRespond
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

    @POST
    suspend fun pushVoucher(@Url url: String, @Body list: List<Voucher>): HttpRespond<String>

    @GET
    suspend fun pullVoucher(
        @Url url: String, @Query("t") syncAt: String
    ): HttpRespond<List<Voucher>>

    @POST
    suspend fun pushSplit(@Url url: String, @Body list: List<Split>): HttpRespond<String>

    @GET
    suspend fun pullSplit(
        @Url url: String, @Query("t") syncAt: String
    ): HttpRespond<List<Split>>

    @POST
    suspend fun pushAccount(@Url url: String, @Body account: List<Account>): HttpRespond<String>

    @GET
    suspend fun pullAccount(
        @Url url: String, @Query("t") syncAt: String
    ): HttpRespond<List<Account>>

    @POST
    suspend fun pushThing(@Url url: String, @Body list: List<Thing>): HttpRespond<String>

    @GET
    suspend fun pullThing(
        @Url url: String, @Query("t") syncAt: String
    ): HttpRespond<List<Thing>>

    @POST
    suspend fun pushDebt(@Url url: String, @Body list: List<Debt>): HttpRespond<String>

    @GET
    suspend fun pullDebt(
        @Url url: String, @Query("t") syncAt: String
    ): HttpRespond<List<Debt>>

    @POST
    suspend fun pushRepay(@Url url: String, @Body list: List<Repay>): HttpRespond<String>

    @GET
    suspend fun pullRepay(
        @Url url: String, @Query("t") syncAt: String
    ): HttpRespond<List<Repay>>

    @POST
    suspend fun pushAsset(@Url url: String, @Body list: List<Asset>): HttpRespond<String>

    @GET
    suspend fun pullAsset(
        @Url url: String, @Query("t") syncAt: String
    ): HttpRespond<List<Asset>>

    @POST
    suspend fun pushAssetLog(@Url url: String, @Body list: List<AssetLog>): HttpRespond<String>

    @GET
    suspend fun pullAssetLog(
        @Url url: String, @Query("t") syncAt: String
    ): HttpRespond<List<AssetLog>>

    @POST
    suspend fun pushTodo(@Url url: String, @Body list: List<Todo>): HttpRespond<String>

    @GET
    suspend fun pullTodo(
        @Url url: String, @Query("t") syncAt: String
    ): HttpRespond<List<Todo>>

    @POST
    suspend fun pushTrade(@Url url: String, @Body list: List<Trade>): HttpRespond<String>

    @GET
    suspend fun pullTrade(
        @Url url: String, @Query("t") syncAt: String
    ): HttpRespond<List<Trade>>

    @POST
    suspend fun pushBalance(@Url url: String, @Body list: List<Balance>): HttpRespond<String>

    @GET
    suspend fun pullBalance(
        @Url url: String, @Query("t") syncAt: String
    ): HttpRespond<List<Balance>>

    @POST
    suspend fun pushBill(@Url url: String, @Body list: List<Bill>): HttpRespond<String>

    @GET
    suspend fun pullBill(
        @Url url: String, @Query("t") syncAt: String
    ): HttpRespond<List<Bill>>

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