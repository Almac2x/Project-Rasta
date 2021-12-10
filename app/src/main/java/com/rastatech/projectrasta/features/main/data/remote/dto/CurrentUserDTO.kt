package com.rastatech.projectrasta.features.main.data.remote.dto

data class CurrentUserDTO(

    val avatar: String?,
    val email : String,
    val first_name: String,
    val last_name: String,
    val phone_number : String,
    val rasta_gems_balance : Int,
    val user_id : Int,
    val username:String

)