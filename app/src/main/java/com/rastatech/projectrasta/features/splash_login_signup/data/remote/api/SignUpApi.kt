package com.rastatech.projectrasta.features.splash_login_signup.data.remote.api

import com.rastatech.projectrasta.features.splash_login_signup.data.remote.dto.UserRequestDTO
import com.rastatech.projectrasta.features.splash_login_signup.data.remote.dto.UserResponseDTO
import retrofit2.http.Body
import retrofit2.http.POST

interface SignUpApi {

    //Api Call Sample
    //https://shielded-retreat-23705.herokuapp.com/api/auth/signup

    @POST("api/auth/signup") // API Call for Adding User to Remote
    suspend fun createUser(@Body user: UserRequestDTO)


}