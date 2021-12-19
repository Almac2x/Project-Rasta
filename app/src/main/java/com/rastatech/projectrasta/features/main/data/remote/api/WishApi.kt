package com.rastatech.projectrasta.features.main.data.remote.api

import retrofit2.Response
import com.rastatech.projectrasta.features.main.data.remote.dto.CreateWishRequestDTO
import com.rastatech.projectrasta.features.main.data.remote.dto.DonatorDTO
import com.rastatech.projectrasta.features.main.data.remote.dto.WishDTO
import com.rastatech.projectrasta.features.main.data.util.ApiKey
import retrofit2.http.*



interface WishApi {





    @GET("/api/wishes/{${ApiKey.WishID.value}}/donators")
    suspend fun getDonators(@Header(ApiKey.Authorization.value) token: String,
                        @Path(ApiKey.WishID.value)wishID : Int
    ): Response<List<DonatorDTO>>

    /**
     * Swagger: fetchWish
     * Tested? = Yes
     * Used? = No
     */
    //Gets a single Wish

    @GET("/api/wishes/{${ApiKey.WishID.value}}")
    suspend fun getWish(@Header(ApiKey.Authorization.value) token: String,
                        @Path(ApiKey.WishID.value)wishID : Int
    ): Response<WishDTO>

    /**
     * Gets the All Wishes HomeScreen Wishes to be Displayed
     * Used? = Yes
     * Tested? = Yes
     * Swagger = fetchWishes
     */

    @GET("/api/wishes")
    suspend fun  getHomeScreenWishes(@Header(ApiKey.Authorization.value) token: String
    ): Response<List<WishDTO>>

    @GET("/api/wishes/liked/{${ApiKey.UserID.value}}")
    suspend fun getWishLikedByAUser(@Header(ApiKey.Authorization.value) token: String,
                                    @Path(ApiKey.UserID.value)userID : Int
    ): Response<List<WishDTO>>


    @GET("/api/wishes")
    suspend fun  getFilteredWishes(@Header(ApiKey.Authorization.value) token: String,
                                   @Query(ApiKey.Sort.value) sort: String,
                                   @Query(ApiKey.Direction.value) direction: String
    ): Response<List<WishDTO>>

    @GET("/api/wishes")
    suspend fun  getSearchWish(@Header(ApiKey.Authorization.value) token: String,
                                   @Query(ApiKey.Search.value) search: String,
    ): Response<List<WishDTO>>

    /**
     * Swagger:fetchWishesDonatedByUser
     * Used? =
     * Tested? = Yes
     */
    //Gets the WishList of Donated Wishes by the User Specified
    @GET("/api/wishes/donated/{${ApiKey.UserID.value}}")
    suspend fun getAUserWishListDonated(@Header(ApiKey.Authorization.value) token: String,
                                        @Path(ApiKey.UserID.value) userID: Int
    ):Response<List<WishDTO>>


    /**
     * Swagger:fetchWishesGrantedByUser
     * Used? = Yes
     * Tested? = Yes
     */
    //Gets the WishList of a User that is Fulfilled
    @GET("/api/wishes/fulfilled/{${ApiKey.UserID.value}}/?limit=888")
    suspend fun getAUserWishListFulfilled(@Header(ApiKey.Authorization.value) token: String,
                                          @Path(ApiKey.UserID.value) userID: Int

    ):Response<List<WishDTO>>

    /**
     * SwaggerName: fetchActiveWishesByUser
     * Used? = Yes
     * Tested? = Yes
     */
    @GET("/api/wishes/active/{${ApiKey.UserID.value}}/?limit=888")
    suspend fun getAUserActiveWishList(@Header(ApiKey.Authorization.value) token: String,
                                       @Path(ApiKey.UserID.value) userID: Int
    ):Response<List<WishDTO>>


    /**
     *
     * Gets  the wish list of a specific user
     * Swagger: fetchWishesByUser
     * Used? = Yes
     * Tested? = Yes
     */

    @GET("/api/wishes/user/{${ApiKey.UserID.value}}")
    suspend fun getWishListOfAUser(@Header(ApiKey.Authorization.value) token: String,
                                   @Path(ApiKey.UserID.value) userID: Int
    ): Response<List<WishDTO>>

    @GET("/api/wishes/status/{${ApiKey.UserID.value}}")
    suspend fun getWishStatus(@Header(ApiKey.Authorization.value) token: String,
                              @Path(ApiKey.UserID.value) userID: Int

    ): Response<Map<String,Int>>


    //Post//////////////
    /**
     * Tested? = Yes
     * Used? = NO
     */

    //Current User Creates a wish
    @POST("/api/wishes")
    suspend fun createAWish(@Header(ApiKey.Authorization.value) token: String,
                            @Body createWish: CreateWishRequestDTO
    ): Response<CreateWishRequestDTO>

    /**
     * Tested? = Yes
     * Used? = No
     */

    // current user likes a wish
    @POST("/api/wishes/{${ApiKey.WishID.value}}/like")
    suspend fun likeAWish(@Header(ApiKey.Authorization.value) token: String,
                          @Path(ApiKey.WishID.value)wishID : Int
    ): Response<Unit>

    /**
     * Tested? = No
     * Used? = No
     */

    // TODO: Did pa nilagay sa WishRespository
    //Get All Votes of a User
    @POST("/api/wishes/{${ApiKey.WishID.value}}/vote")
    suspend fun getAllVotes(@Header(ApiKey.Authorization.value) token: String,
                            @Path(ApiKey.WishID.value)wishID : Int
    ):Response<List<Map<String,Int>>> // Parang mali ito please check with backend

    //Donate to a Wish
    @POST("/api/donate/{${ApiKey.WishID.value}}") // Issue with backend ata
    suspend fun donateToAWish(@Header(ApiKey.Authorization.value) token: String,
                              @Path(ApiKey.WishID.value)wishID : Int,
                              @Body amount: Map<String,Int>
    ):Response<Unit>

    /**
     * Tested? = Yes
     * Used? = No
     */

    //Vote on a certain Wish
    //A Map must be pass on: Ex params = mapOf("vote_type" to "UPVOTE") or DOWNVOTE
    @POST("/api/wishes/{${ApiKey.WishID.value}}/vote")
    suspend fun voteAWish (@Header(ApiKey.Authorization.value) token: String,
                           @Path("${ApiKey.WishID.value}") wishID: Int,
                           @Body voteType: Map<String, String>
    ):Response<Unit>

    /**
     * Tested? = No
     * Used? = No
     */
    ///PUT///////////
    @PUT("/api/wishes/{${ApiKey.WishID.value}}")
    suspend fun updateAWish(@Header(ApiKey.Authorization.value) token: String,
                            @Path("${ApiKey.WishID.value}") wishID: Int,
                            @Body createWish: CreateWishRequestDTO
    ):Response<Unit>

    //DELETE///////////////////


    /**
     * Tested? = No
     * Used? = No
     * This Delete Wishes
     * Swagger: deleteWish
     */
    @DELETE("/api/wishes/{${ApiKey.WishID.value}}")
    suspend fun deleteAWish(@Header(ApiKey.Authorization.value) token: String,
                            @Path(ApiKey.WishID.value)wishID : Int
    ):Response<Unit>

}