package com.rastatech.projectrasta.api

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

private const val BASE_URL :String = "https://barrel-april-delhi-blocked.trycloudflare.com/" // base url goes here TEMP ONLY!!!!!
object RetrofitInstance {

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create()) // creates Moshi as our data converter
            .build()
    }

    val api: RastaAPI by lazy { // since the above is private. We created this so we can create a public instance
        retrofit.create(RastaAPI::class.java)
    }
}