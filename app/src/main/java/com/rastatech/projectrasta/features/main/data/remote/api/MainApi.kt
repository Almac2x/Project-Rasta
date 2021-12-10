package com.rastatech.projectrasta.features.main.data.remote.api

import com.rastatech.projectrasta.features.main.data.remote.dto.CreateWishResponseDTO
import com.rastatech.projectrasta.features.main.data.remote.dto.CurrentUserDTO
import com.rastatech.projectrasta.features.main.data.remote.dto.WishDTO
import retrofit2.http.*


const val AUTHORIZATION_BEARER_KEY = "Authorization"
const val DESCRIPTION_KEY = "description"
const val IMAGE_URL_KEY = "image_url"

interface MainApi {

    /**
    "Content-Type": "application/json",
    "Authorization": "Bearer \(authToken!.accessToken)"
     */


    /**
     * User Api Calls
     */
    // Gets the CurrentUsers Profile Details
    @GET("api/users/own")
    suspend fun getOwnProfile(@Header(AUTHORIZATION_BEARER_KEY) token: String):CurrentUserDTO


    /**
     * Wishes Api Calls
     */

    //GET
    @GET("/api/users/balance")
    suspend fun getUserBalance(@Header(AUTHORIZATION_BEARER_KEY) token: String): Map<String,Int>

    //POST





}