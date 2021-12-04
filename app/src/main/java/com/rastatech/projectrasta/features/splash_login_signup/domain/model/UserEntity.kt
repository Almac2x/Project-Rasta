package com.rastatech.projectrasta.features.splash_login_signup.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "user_table")
data class UserEntity(

    //
    @PrimaryKey val id : Long,
    val user_name : String,
    val first_name : String,
    val last_name: String,
    val email : String,
    val rasta_gem_balance: Int,
    val avatar: String,

    // Tokens of user
    val refresh_token :String,
    val token: String
){


}

class InvalidUserException(message: String): Exception(message)
