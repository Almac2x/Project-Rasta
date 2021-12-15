package com.rastatech.projectrasta.features.main.data.repository


import com.rastatech.projectrasta.features.main.data.remote.api.WishApi
import com.rastatech.projectrasta.features.main.data.remote.dto.CreateWishRequestDTO
import com.rastatech.projectrasta.features.main.data.remote.dto.WishDTO
import com.rastatech.projectrasta.features.main.data.util.ApiKey
import com.rastatech.projectrasta.features.main.domain.repository.WishRepository
import com.rastatech.projectrasta.features.main.domain.util.VoteType
import retrofit2.Response

private const val TAG = "WishRepositoryImp"
private const val BEARER_KEY ="Bearer"
class WishRepositoryImp(
    private val api: WishApi

): WishRepository {
    override suspend fun getHomeScreenWishes(token: String): Response<List<WishDTO>> {
        return api.getHomeScreenWishes(token = "${ApiKey.Bearer.value}$token")
    }

    override suspend fun getWish(token: String, wishID: Int): Response<WishDTO> {
        return api.getWish(token = "${ApiKey.Bearer.value}$token",
                                        wishID = wishID)
    }

    override suspend fun getWishListOfAUser(token: String, userID: Int): Response<List<WishDTO>> {
        return api.getWishListOfAUser(token = "${ApiKey.Bearer.value}$token",
                                      userID = userID)
    }

    override suspend fun createAWish(token: String, description: String,
                                     imageURL: String, rastaGemsRequired: Int,
                                     wishName: String
    ): Response<CreateWishRequestDTO> {
        val createWish = CreateWishRequestDTO( description = description,image_url = imageURL,
                                               rastagems_required = rastaGemsRequired, wish_name = wishName
        )
        return api.createAWish(token = "${ApiKey.Bearer.value}$token", createWish = createWish)
    }

    override suspend fun likeAWish(token: String, wishID: Int): Response<Unit>{
        return api.likeAWish(token = "${ApiKey.Bearer.value}$token", wishID = wishID)
    }

    override suspend fun donateToAWish(token: String, wishID: Int, amount: Int): Response<Unit>{
        return api.donateToAWish(token = "${ApiKey.Bearer.value}$token", wishID = wishID,
                                 amount = mapOf("${ApiKey.Amount.value}" to amount))  //get amount key from sealed class
    }

    override suspend fun voteAWish(token: String, wishID: Int, voteType: VoteType):Response<Unit>  {
        return api.voteAWish(token = "${ApiKey.Bearer.value}$token", wishID = wishID,
                             voteType = mapOf("${ApiKey.VoteType.value}" to voteType.value)) // get the Vote Type from sealed Class
    }

    override suspend fun deleteAWish(token: String, wishID: Int): Response<Unit> {
        return api.deleteAWish(token = "${ApiKey.Bearer.value}$token", wishID = wishID)
    }

    override suspend fun getAllVotes() {
        TODO("Not yet implemented")
    }

}