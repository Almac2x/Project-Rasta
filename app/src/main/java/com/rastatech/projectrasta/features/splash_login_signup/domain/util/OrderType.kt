package com.rastatech.projectrasta.features.splash_login_signup.domain.util

sealed class OrderType{

    object Ascending: OrderType()
    object Descending: OrderType()
}
