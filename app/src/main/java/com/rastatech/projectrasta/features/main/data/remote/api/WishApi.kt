package com.rastatech.projectrasta.features.main.data.remote.api

import retrofit2.Response
import com.rastatech.projectrasta.features.main.data.remote.dto.CreateWishRequestDTO
import com.rastatech.projectrasta.features.main.data.remote.dto.WishDTO
import com.rastatech.projectrasta.features.main.data.util.ApiKey
import retrofit2.http.*



interface WishApi {

    //Gets the HomeScreen Wishes to be Displayed
    @GET("/api/wishes")
    suspend fun  getHomeScreenWishes(@Header(ApiKey.AuthorizationBearer.value) token: String
    ): Response<List<WishDTO>>

    //Gets a single Wish
    @GET("/api/wishes/{${ApiKey.WishID.value}}")
    suspend fun getWish(@Header(ApiKey.AuthorizationBearer.value) token: String,
                        @Path(ApiKey.WishID.value)wishID : Int
    ): Response<WishDTO>

    //Post//////////////

    //Current User Creates a wish
    @POST("/api/wishes")
    suspend fun createAWish(@Header(ApiKey.AuthorizationBearer.value) token: String,
                            @Body createWish: CreateWishRequestDTO
    ): Response<CreateWishRequestDTO>

    // current user likes a wish
    @POST("/api/wishes/{${ApiKey.WishID.value}}/like")
    suspend fun likeAWish(@Header(ApiKey.AuthorizationBearer.value) token: String,
                          @Path(ApiKey.WishID.value)wishID : Int
    )

    // TODO: Did pa nilagay sa WishRespository
    //Get All Votes of a User
    @POST("/api/wishes/{${ApiKey.WishID.value}}/vote")
    suspend fun getAllVotes(@Header(ApiKey.AuthorizationBearer.value) token: String,
                            @Path(ApiKey.WishID.value)wishID : Int
    ):Response<List<Map<String,Int>>> // Parang mali ito please check with backend

    //Donate to a Wish
    @POST("/api/donate/{${ApiKey.WishID.value}}") // Issue with backend ata
    suspend fun donateToAWish(@Header(ApiKey.AuthorizationBearer.value) token: String,
                              @Path(ApiKey.WishID.value)wishID : Int,
                              @Body amount: Map<String,Int>
    )

    //Vote on a certain Wish
    //A Map must be pass on: Ex params = mapOf("vote_type" to "UpVote") or DownVote
    @POST("/api/wishes/{${ApiKey.WishID.value}}/vote")
    suspend fun voteAWish (@Header(ApiKey.AuthorizationBearer.value) token: String,
                           @Path("${ApiKey.WishID.value}") wishID: Int,
                           @Body voteType: Map<String, String>
    )

    //DELETE///////////////////
    @DELETE("/api/wishes/{${ApiKey.WishID.value}}")
    suspend fun deleteAWish(@Header(ApiKey.AuthorizationBearer.value) token: String,
                           @Path(ApiKey.WishID.value)wishID : Int
    )

}