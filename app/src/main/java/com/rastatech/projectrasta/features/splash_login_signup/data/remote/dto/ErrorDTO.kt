package com.rastatech.projectrasta.features.splash_login_signup.data.remote.dto

data class ErrorDTO (
    val phone_number : String? = "None",
    val email : String? = "None",
    val username: String? = "None"

        ){
}