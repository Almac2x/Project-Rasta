package com.rastatech.projectrasta.features.splash_login_signup.domain.repository


import com.rastatech.projectrasta.features.splash_login_signup.data.local.entity.UserEntity
import com.rastatech.projectrasta.features.splash_login_signup.data.remote.dto.UserRequestDTO
import kotlinx.coroutines.flow.Flow

interface UserRepository {

    // Edit User local Calls Repository
    fun getUsers():Flow<List<UserEntity>>

    suspend fun getUserById(id: Int) : UserEntity?

    suspend fun insertUser(user: UserEntity) // change to insertUserLocal

    suspend fun deleteUser( user: UserEntity)

    //User Api calls Repository

    suspend fun getLoginTokenApiRequest(username: String, password: String)
    suspend fun createUserApiRequest(user: UserRequestDTO)


}