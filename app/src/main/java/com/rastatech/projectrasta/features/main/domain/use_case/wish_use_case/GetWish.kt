package com.rastatech.projectrasta.features.main.domain.use_case.wish_use_case

import com.rastatech.projectrasta.features.main.data.remote.dto.WishDTO
import com.rastatech.projectrasta.features.main.domain.repository.WishRepository
import retrofit2.Response


private const val TAG = "GetWish Use-Case"
class GetWish(

    private val repository : WishRepository

) {

    suspend operator fun invoke(token: String, wishID: Int): Response<WishDTO> {

       return repository.getWish(token = token,
                            wishID = wishID)

    }
}