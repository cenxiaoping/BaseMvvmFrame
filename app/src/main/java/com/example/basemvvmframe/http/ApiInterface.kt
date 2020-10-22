package com.example.basemvvmframe.http

import com.example.basemvvmframe.bean.BaseBean
import com.example.basemvvmframe.bean.UserInfo
import com.example.basemvvmframe.http.converter.GsonConverterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiInterface {
    @FormUrlEncoded
    @POST("login")
    suspend fun login(
        @Field("phone") phone: String,
        @Field("smsCode") smsCode: String,
        @Field("imageCode") imageCode: String
    ): BaseBean<UserInfo>

    companion object {
        private const val BASE_URL = "http://rap2api.taobao.org/app/mock/118371/"

        fun create(): ApiInterface {
            val logger = LoggingInterceptor()

            val client = OkHttpClient.Builder()
                .addInterceptor(logger)
                .build()

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiInterface::class.java)
        }
    }
}