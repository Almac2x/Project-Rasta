package com.rastatech.projectrasta.features.main.data.remote.api

import com.rastatech.projectrasta.features.main.data.remote.dto.CommentsDTO
import com.rastatech.projectrasta.features.main.data.util.ApiKey
import retrofit2.Response
import retrofit2.http.*

interface CommentApi {


    @GET("/api/wishes/{${ApiKey.WishID.value}}/comments")
    suspend fun getComments(@Header(ApiKey.Authorization.value) token: String,
                            @Path(ApiKey.WishID.value)wishID : Int,
    ): Response<List<CommentsDTO>>


    @POST("/api/wishes/{${ApiKey.WishID.value}}/comments")
    suspend fun comment(@Header(ApiKey.Authorization.value) token: String,
                        @Body comment : Map<String,String>,
                        @Path(ApiKey.WishID.value)wishID : Int,
    )
}