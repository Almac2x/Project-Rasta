package com.rastatech.projectrasta.features.splash_login_signup.domain.model

data class User (

    val user_name: String,
    val email : String,
    val first_name: String,
    val last_name: String,
    val phone_number: String,
    val avatar: String,
    val rasta_gem_balance: Int,

        ){
}