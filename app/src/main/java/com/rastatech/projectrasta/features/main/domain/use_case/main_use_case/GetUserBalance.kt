package com.rastatech.projectrasta.features.main.domain.use_case.main_use_case

import com.rastatech.projectrasta.features.main.domain.repository.MainRepository
import retrofit2.Response

class GetUserBalance(
    private val repository : MainRepository
){

    suspend operator fun invoke(token : String): Response<Map<String, Int>> {

        return repository.getUserBalance(token = token)
    }
}