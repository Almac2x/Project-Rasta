package com.rastatech.projectrasta.features.main.data.remote.api

import com.rastatech.projectrasta.features.main.data.remote.dto.CreateWishResponseDTO
import com.rastatech.projectrasta.features.main.data.remote.dto.WishDTO
import retrofit2.http.*


const val RASTAGEMS_REQUIRED_KEY ="rastagems_required"
const val WISH_NAME_KEY = "wish_name"
const val WISH_ID_KEY = "wish_id"
const val AMOUNT_KEY = "amount"
interface WishApi {

    //Gets the HomeScreen Wishes to be Displayed
    @GET("/api/wishes")
    suspend fun  getHomeScreenWishes(@Header(AUTHORIZATION_BEARER_KEY) token: String): List<WishDTO>

    //Gets a single Wish
    @GET("/api/wishes/{$WISH_ID_KEY}")
    suspend fun getWish(@Header(AUTHORIZATION_BEARER_KEY) token: String,
                        @Path(WISH_ID_KEY)wishID : Int
    ): WishDTO

    //Post//////////////

    //Current User Creates a wish
    @POST("/api/wishes")
    suspend fun createWish(@Header(AUTHORIZATION_BEARER_KEY) token: String,
                           @Body createWish: CreateWishResponseDTO
    ) : CreateWishResponseDTO

    // current user likes a wish
    @POST("/api/wishes/{$WISH_ID_KEY}/like")
    suspend fun likeWish(@Header(AUTHORIZATION_BEARER_KEY) token: String,
                         @Path(WISH_ID_KEY)wishID : Int
    )

    //Get All Votes of a User
    @POST("/api/wishes/{wish_id}/vote")
    suspend fun getAllVotes(@Header(AUTHORIZATION_BEARER_KEY) token: String,
                            @Path(WISH_ID_KEY)wishID : Int
    )

    //Donate to a Wish
    @POST("/api/donate/{$WISH_ID_KEY}") // Issue with backend ata
    suspend fun donateToAWish(@Header(AUTHORIZATION_BEARER_KEY) token: String,
                              @Path(WISH_ID_KEY)wishID : Int,
                              @Body amount: Map<String,Int>
    )

    //Vote on a certain Wish
    //A Map must be pass on: Ex params = mapOf("vote_type" to "UPVOTE") or DOWNVOTE
    @POST("/api/wishes/{$WISH_ID_KEY}/vote")
    suspend fun voteWish (@Header(AUTHORIZATION_BEARER_KEY) token: String,
                          @Path("$WISH_ID_KEY") wishID: Int,
                          @Body voteType: Map<String, String>
    )

    //DELETE///////////////////
    @DELETE("/api/wishes/{$WISH_ID_KEY}")
    suspend fun deleteWish(@Header(AUTHORIZATION_BEARER_KEY) token: String,
                           @Path(WISH_ID_KEY)wishID : Int
    )


}