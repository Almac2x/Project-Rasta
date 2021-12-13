package com.rastatech.projectrasta.features.splash_login_signup.data.remote.dto

data class ErrorDTO (
    val phone_number : String? = "NONE",
    val email : String? = "NONE",
    val username: String? = "NONE"

        ){
}