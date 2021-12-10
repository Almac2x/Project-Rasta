package com.rastatech.projectrasta.features.main.domain.use_case.wish_use_case

import com.rastatech.projectrasta.features.main.data.remote.dto.CreateWishRequestDTO
import com.rastatech.projectrasta.features.main.domain.repository.WishRepository
import retrofit2.Response

class CreateAWish(
    private val repository: WishRepository
) {
    suspend operator fun invoke(token: String, description: String,
                                imageURL: String, rastaGemsRequired: Int,
                                wishName: String): Response<CreateWishRequestDTO> {
        return repository.createAWish(token = token, description = description,
                               imageURL = imageURL, rastaGemsRequired = rastaGemsRequired,
                               wishName = wishName
        )
    }
}