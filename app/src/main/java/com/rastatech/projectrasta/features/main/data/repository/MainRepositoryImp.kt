package com.rastatech.projectrasta.features.main.data.repository

import com.rastatech.projectrasta.features.main.data.remote.api.MainApi
import com.rastatech.projectrasta.features.main.data.remote.dto.UserDTO
import com.rastatech.projectrasta.features.main.data.util.ApiKey
import com.rastatech.projectrasta.features.main.domain.repository.MainRepository
import retrofit2.Response


private const val TAG = "MainRepositoryImp"

class MainRepositoryImp(

    private val api: MainApi

): MainRepository {

    override suspend fun getOwnProfile(token: String): Response<UserDTO> {
        return api.getOwnProfile(token = "${ApiKey.Bearer.value}$token")
    }
    override suspend fun getUserBalance(token: String): Response<Map<String, Int>> {
        return api.getUserBalance(token = "${ApiKey.Bearer.value}$token")
    }


}