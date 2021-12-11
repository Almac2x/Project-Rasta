package com.rastatech.projectrasta.features.main.data.remote.api

import retrofit2.Response
import com.rastatech.projectrasta.features.main.data.remote.dto.CurrentUserDTO
import com.rastatech.projectrasta.features.main.data.util.ApiKey
import retrofit2.http.*


interface MainApi {

    /**
    "Content-Type": "application/json",
    "Authorization": "Bearer \(authToken!.accessToken)"
     */


    /**
     * User Api Calls
     */

    /**
     * TODO
     *
     * @param token
     * @return
     */
    // Gets the CurrentUsers Profile Details
    @GET("api/users/own")
    suspend fun getOwnProfile(@Header(ApiKey.Authorization.value) token: String
    ): Response<CurrentUserDTO>


    /**
     * Wishes Api Calls
     */

    //GET

    /**
     * TODO
     *
     * @param token
     * @return
     */
    @GET("/api/users/balance")
    suspend fun getUserBalance(@Header(ApiKey.Authorization.value) token: String
    ): Response<Map<String,Int>>

    //POST





}