package com.rastatech.projectrasta.features.main.domain.repository

import com.rastatech.projectrasta.features.main.data.remote.dto.CreateWishRequestDTO
import com.rastatech.projectrasta.features.main.data.remote.dto.WishDTO
import com.rastatech.projectrasta.features.main.domain.util.VoteType

import retrofit2.Response

interface WishRepository {

    //Wish Database Calls

    //Wish Api Calls
    //Get
    suspend fun getHomeScreenWishes(token:String) : Response<List<WishDTO>>
    suspend fun getWish(token : String,wishID: Int) : Response<WishDTO>
    suspend fun getWishListOfAUser(token: String, userID: Int): Response<List<WishDTO>>

    //Post
    suspend fun createAWish(token: String, description: String,
                            imageURL: String, rastaGemsRequired: Int,
                            wishName: String): Response<CreateWishRequestDTO>
    suspend fun likeAWish(token: String, wishID : Int): Response<Unit>
    suspend fun donateToAWish(token:String, wishID: Int, amount : Int): Response<Unit>

    suspend fun voteAWish(token: String,wishID: Int, voteType: VoteType):Response<Unit>

    //Delete
    suspend fun deleteAWish(token: String, wishID: Int): Response<Unit>


    //Di ko alam kung saan gagamitin
    suspend fun getAllVotes()



}