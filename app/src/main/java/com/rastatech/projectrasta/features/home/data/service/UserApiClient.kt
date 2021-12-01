package com.rastatech.projectrasta.features.home.data.service

import com.rastatech.projectrasta.features.home.data.data_source.UserApiService
import java.util.concurrent.TimeUnit
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class UserApiClient {

    private val client: OkHttpClient = OkHttpClient.Builder()
        /*.addNetworkInterceptor(this)*/
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        /*.addInterceptor(logging)*/
        .build()

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private var service: UserApiService? = null

//    init {
////        client = OkHttpClient.Builder()
////            /*.addNetworkInterceptor(this)*/
////            .connectTimeout(30, TimeUnit.SECONDS)
////            .readTimeout(30, TimeUnit.SECONDS)
////            .writeTimeout(30, TimeUnit.SECONDS)
////            /*.addInterceptor(logging)*/
////            .build()
//
//    }

    fun getService(): UserApiService {
        if (service == null)
            service = retrofit.create(UserApiService::class.java)
        return service!!
    }

    companion object {
        const val baseUrl = "https://jsonplaceholder.typicode.com/"
    }

}