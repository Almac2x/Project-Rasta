package com.rastatech.projectrasta.features.splash_login_signup.data.remote.dto

data class UserDTO(

    val user_name: String,
    val email : String,
    val first_name: String,
    val last_name: String,
    val password: String,
    val phone_numebr: String,
) {
}