package com.rastatech.projectrasta.features.splash_login_signup.presentation.login


import com.rastatech.projectrasta.features.splash_login_signup.data.local.entity.UserEntity
import com.rastatech.projectrasta.features.splash_login_signup.domain.util.UserOrder

sealed class LoginEvents{

    data class Order(val userOrder: UserOrder) : LoginEvents()
    data class DeleteLogin(val user:UserEntity): LoginEvents()
    data class Login(val userName: String, val password: String) : LoginEvents()

    //Add Here Login Api Event


    object RestoreLogin: LoginEvents() // This might not be used in the app // This does not affect the database
    object ToggleOrderSection: LoginEvents()
}
