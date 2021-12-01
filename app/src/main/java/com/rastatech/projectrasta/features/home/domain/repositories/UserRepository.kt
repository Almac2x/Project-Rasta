package com.rastatech.projectrasta.features.home.domain.repositories

import com.rastatech.projectrasta.features.home.data.service.UserApiClient
import com.rastatech.projectrasta.features.home.domain.entities.UserEntity

class UserRepository(
    private val apiClient: UserApiClient = UserApiClient(),
) {

    /**
     * Retrieves users from Remote Source
     * */
    suspend fun getUsers(): List<UserEntity> = apiClient.getService().getUsers()
}