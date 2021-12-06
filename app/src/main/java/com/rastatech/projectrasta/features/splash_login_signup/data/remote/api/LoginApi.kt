package com.rastatech.projectrasta.features.splash_login_signup.data.remote.api



import com.rastatech.projectrasta.features.splash_login_signup.data.remote.dto.LoginUserDTO
import com.rastatech.projectrasta.features.splash_login_signup.data.remote.dto.TokenDTO
import com.rastatech.projectrasta.features.splash_login_signup.data.remote.dto.UserResponseDTO
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface LoginApi {

    ////https://shielded-retreat-23705.herokuapp.com/api/auth/login?username=value&password=value


    @GET("users")
    suspend fun getPosts(): List<UserResponseDTO>

    @POST("api/auth/login")
    suspend fun getToken(@Query("username") username: String, @Query("password") password: String): Response<TokenDTO> // returns a TokenDTO


}