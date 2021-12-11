package com.rastatech.projectrasta.features.main.domain.use_case.wish_use_case

import com.rastatech.projectrasta.features.main.data.remote.dto.WishDTO
import com.rastatech.projectrasta.features.main.domain.repository.WishRepository
import retrofit2.Response

class GetUserWishList(
    private val repository: WishRepository
) {
    suspend operator fun invoke(token: String, userID: Int): Response<List<WishDTO>>{
        return repository.getWishListOfAUser(token = token, userID = userID)
    }
}