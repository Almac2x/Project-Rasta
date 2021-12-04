package com.rastatech.projectrasta.features.splash_login_signup.presentation.util

import com.rastatech.projectrasta.features.splash_login_signup.domain.model.UserEntity
import com.rastatech.projectrasta.features.splash_login_signup.domain.util.UserOrder

sealed class UserEvents{

    data class Order(val userOrder: UserOrder) : UserEvents()
    data class DeleteUser(val user:UserEntity): UserEvents()

    object RestoreUser:UserEvents() // This might not be used in the app // This does not affect the database
    object ToggleOrderSection: UserEvents()
}
