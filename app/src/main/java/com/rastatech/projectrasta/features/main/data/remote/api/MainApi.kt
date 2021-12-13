package com.rastatech.projectrasta.features.main.data.remote.api

import retrofit2.Response
import com.rastatech.projectrasta.features.main.data.remote.dto.CurrentUserDTO
import com.rastatech.projectrasta.features.main.data.remote.dto.TransactionDTO
import com.rastatech.projectrasta.features.main.data.util.ApiKey
import retrofit2.http.*


interface MainApi {


    /**
     * Tested? = Yes
     * Used? = Yes
     */

    // Gets the CurrentUsers Profile Details
    @GET("api/users/own")
    suspend fun getOwnProfile(@Header(ApiKey.Authorization.value) token: String
    ): Response<CurrentUserDTO>


    /**
     * Wishes Api Calls
     * Tested? = Yes
     * Used? = No
     */

    //GET
    
    @GET("/api/users/balance")
    suspend fun getUserBalance(@Header(ApiKey.Authorization.value) token: String
    ): Response<Map<String,Int>>

    @GET("/api/rastagem/history")
    suspend fun getTransactionHistory(@Header(ApiKey.Authorization.value) token: String

    ) :Response<TransactionDTO>// ADD HERE THE DTO FOR TRANSACTION

    /**
     * Tested? = No
     * Used? = No
     */

    //////////////POST
    @POST("/api/rastagem/add")
    suspend fun addGems(@Header(ApiKey.Authorization.value) token: String,
                        @Body amount: Map<String, Int>
    ):Response<Unit>

    /**
     * Tested? = No
     * Used? = No
     */

    @POST("/api/rastagem/transfer/{${ApiKey.UserName.value}}")
    suspend fun sendGems(@Header(ApiKey.Authorization.value) token: String,
                             @Path(ApiKey.UserName.value) userName: String,
                         @Body amount : Map<String,Int>
    ):Response<Unit>


    /**
     *  Tested? = No
     *  Used? = No
     *   //Same with donateToAWish in Wish Api
     */

    @POST("/api/rastagem/donate/{${ApiKey.WishID.value}}")
    suspend fun donateGemsToAWish(@Header(ApiKey.Authorization.value) token: String,
                                  @Path(ApiKey.WishID.value)wishID : Int,
                                  @Body amount: Map<String,Int>
    ):Response<Unit>







}