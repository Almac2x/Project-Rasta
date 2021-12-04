package com.rastatech.projectrasta.features.splash_login_signup.domain.repository

import com.rastatech.projectrasta.features.splash_login_signup.domain.model.UserEntity
import kotlinx.coroutines.flow.Flow

interface UserRepository {

    fun getUsers():Flow<List<UserEntity>>

    suspend fun getUserById(id: Int) : UserEntity?

    suspend fun insertUser(user: UserEntity)

    suspend fun deleteUser( user: UserEntity)

}