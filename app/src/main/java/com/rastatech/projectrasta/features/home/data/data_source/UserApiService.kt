package com.rastatech.projectrasta.features.home.data.data_source

import com.rastatech.projectrasta.features.home.domain.entities.UserEntity
import retrofit2.http.GET

interface UserApiService {
    @GET("/users")
    suspend fun getUsers(): List<UserEntity>
}