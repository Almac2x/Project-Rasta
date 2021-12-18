package com.rastatech.projectrasta.features.main.domain.repository

import com.rastatech.projectrasta.features.main.data.remote.dto.UserDTO
import retrofit2.Response


//AYUSIN ITONG REPOSITORY DAPAT NAKA BASE SA ENTITY WALANG MAIN REPOSITORY
interface MainRepository {

    //Main Database Calls

    //Main Api Calls
    suspend fun getOwnProfile(token: String): Response<UserDTO>

    suspend fun getUserBalance(token: String): Response<Map<String,Int>>


}