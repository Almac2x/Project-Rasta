package com.rastatech.projectrasta.features.main.data.repository

import com.rastatech.projectrasta.core.remote.api.RetrofitInstance
import com.rastatech.projectrasta.features.main.data.remote.api.MainApi
import com.rastatech.projectrasta.features.main.data.remote.dto.CurrentUserDTO
import com.rastatech.projectrasta.features.main.domain.repository.MainRepository
import retrofit2.Response

private const val TAG = "MainRepositoryImp"
class MainRepositoryImp(

    private val api: MainApi

): MainRepository {
    override suspend fun getOwnProfile(token: String): Response<CurrentUserDTO> {
        return api.getOwnProfile(token = token)
    }
    override suspend fun getUserBalance(token: String): Response<Map<String, Int>> {
        return api.getUserBalance(token = token)
    }


}