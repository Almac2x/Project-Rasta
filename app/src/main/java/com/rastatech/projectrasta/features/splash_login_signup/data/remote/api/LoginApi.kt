package com.rastatech.projectrasta.features.splash_login_signup.data.remote.api


import com.rastatech.projectrasta.features.splash_login_signup.data.remote.dto.UserDTO
import retrofit2.http.GET

interface LoginApi {

    @GET("posts")
    suspend fun getPosts(): List<UserDTO>


}