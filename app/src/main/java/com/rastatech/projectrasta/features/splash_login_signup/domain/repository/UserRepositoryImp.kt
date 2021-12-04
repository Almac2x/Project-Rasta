package com.rastatech.projectrasta.features.splash_login_signup.domain.repository

import com.rastatech.projectrasta.features.splash_login_signup.data.data_source.UserDao
import com.rastatech.projectrasta.features.splash_login_signup.domain.model.UserEntity
import kotlinx.coroutines.flow.Flow

class UserRepositoryImpl (
    private  val dao : UserDao
        ) : UserRepository {


    override fun getUsers(): Flow<List<UserEntity>> {
        return dao.getUsers()
    }

    override suspend fun getUserById(id: Int): UserEntity? {

        return dao.getUserById(id = id)
    }

    override suspend fun insertUser(user: UserEntity) {

        dao.insertUser(user = user)
    }

    override suspend fun deleteUser(user: UserEntity) {

        dao.deleteUser(user = user)
    }
}