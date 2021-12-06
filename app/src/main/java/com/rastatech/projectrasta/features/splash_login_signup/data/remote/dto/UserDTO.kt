package com.rastatech.projectrasta.features.splash_login_signup.data.remote.dto

import com.rastatech.projectrasta.features.splash_login_signup.data.local.entity.UserEntity

data class UserRequestDTO(

    val username: String,
    val email : String,
    val first_name: String,
    val last_name: String,
    val password: String,
    val phone_number: String,
) {


}
data class UserResponseDTO(
    val id : Long,
    val user_name: String,
    val email : String,
    val first_name: String,
    val last_name: String,
    val password: String,
    val phone_number: String,
    val rasta_gem_balance: Int,
    val avatar : String


){
    fun toUserEntity(): UserEntity {
        return UserEntity(
            user_name = user_name,
            email = email,
            first_name = first_name,
            last_name = last_name,
            rasta_gem_balance = rasta_gem_balance,
            id = id,
            avatar = avatar,

            refresh_token = "", // add  here to API call for Login Token
            token = "" // add  here to API call for Login Token

        )
    }

}

