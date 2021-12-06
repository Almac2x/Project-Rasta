package com.rastatech.projectrasta.core.remote.api

import com.rastatech.projectrasta.features.splash_login_signup.data.remote.api.LoginApi
import com.rastatech.projectrasta.features.splash_login_signup.data.remote.api.SignUpApi
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

//private const val BASE_URL = "https://jsonplaceholder.typicode.com/"
private const val BASE_URL = "https://shielded-retreat-23705.herokuapp.com/"
object RetrofitInstance {
    /**
     * Use the Retrofit builder to build a retrofit object using a Moshi converter.
     */
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create()) // Moshi Converter is used
            .build()
    }

    //Instance for LoginApi
    val loginApi: LoginApi by lazy {
        retrofit.create(LoginApi::class.java)
    }


    // Instance for SignUpApi
    val signUpApi: SignUpApi by lazy {
        retrofit.create(SignUpApi::class.java)
    }
}