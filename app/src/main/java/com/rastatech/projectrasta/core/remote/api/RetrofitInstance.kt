package com.rastatech.projectrasta.core.remote.api

import com.rastatech.projectrasta.features.main.data.remote.api.CommentApi
import com.rastatech.projectrasta.features.main.data.remote.api.MainApi
import com.rastatech.projectrasta.features.main.data.remote.api.WishApi
import com.rastatech.projectrasta.features.splash_login_signup.data.remote.api.LoginApi
import com.rastatech.projectrasta.features.splash_login_signup.data.remote.api.SignUpApi
import okhttp3.OkHttpClient
import org.w3c.dom.Comment
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

//private const val BASE_URL = "https://jsonplaceholder.typicode.com/"
private const val BASE_URL = "https://shielded-ridge-49519.herokuapp.com/"
//private const val BASE_URL = "https://secret-rasta-mini-project-ycimv.ondigitalocean.app/"
object RetrofitInstance {

    private val client = OkHttpClient.Builder()
        .connectTimeout(5, TimeUnit.SECONDS)
        .readTimeout(5, TimeUnit.SECONDS)
        .connectTimeout(5, TimeUnit.SECONDS)
        .addInterceptor(HeaderInterceptor())
        .build()

    /**
     * Use the Retrofit builder to build a retrofit object using a Moshi converter.
     */
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
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

    // Instance for MainApi
    val mainApi: MainApi by lazy {
        retrofit.create(MainApi::class.java)
    }

    val wishApi: WishApi by lazy {
        retrofit.create(WishApi::class.java)
    }

    val commentApi : CommentApi by lazy {
        retrofit.create(CommentApi::class.java)
    }


}