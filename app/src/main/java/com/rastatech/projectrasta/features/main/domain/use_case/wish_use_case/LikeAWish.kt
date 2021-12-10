package com.rastatech.projectrasta.features.main.domain.use_case.wish_use_case

import com.rastatech.projectrasta.features.main.domain.repository.WishRepository
import retrofit2.Response

class LikeAWish(
    private val repository: WishRepository
) {

    suspend operator fun invoke (token: String, wishID : Int): Response<Unit> {

        return repository.likeAWish(token = token, wishID = wishID)

    }
}