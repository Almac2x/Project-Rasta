package com.rastatech.projectrasta.features.splash_login_signup.domain.repository

import android.util.Log
import com.rastatech.projectrasta.core.remote.api.RetrofitInstance
import com.rastatech.projectrasta.features.splash_login_signup.data.data_source.UserDao
import com.rastatech.projectrasta.features.splash_login_signup.data.local.entity.UserEntity
import com.rastatech.projectrasta.features.splash_login_signup.data.remote.api.LoginApi
import com.rastatech.projectrasta.features.splash_login_signup.data.remote.api.SignUpApi
import com.rastatech.projectrasta.features.splash_login_signup.data.remote.dto.LoginUserDTO
import com.rastatech.projectrasta.features.splash_login_signup.data.remote.dto.UserRequestDTO
import kotlinx.coroutines.flow.Flow

const val TAG = "UserRepositoryImpl"

class UserRepositoryImpl (
    private  val dao : UserDao,
    private val retrofit : RetrofitInstance
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

    override suspend fun getLoginTokenApiRequest(username: String, password: String) {

        Log.i(TAG, "Function: getLoginTokenApiRequest " +
                "\nUsername Sent: $username" +
                "\nPassword Sent: $password ")

        val token =  retrofit.loginApi.getToken(
                username = username,
                password = password
        )

        if(token.isSuccessful){
            Log.i(TAG, "Token: ${token.body()?.access_token} \n Refresh Token: ${token.body()?.refresh_token}")
        }else{

            when(token.code()){

                403 ->{

                }


            }
        }



    }
    override suspend fun createUserApiRequest(user: UserRequestDTO) {
        retrofit.signUpApi.createUser(user = user)
    }
}