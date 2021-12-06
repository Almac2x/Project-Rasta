package com.rastatech.projectrasta.features.splash_login_signup.domain.util

sealed class UserOrder(val orderType: OrderType) {

    class FirstName(orderType: OrderType) : UserOrder(orderType)
    class LastName(orderType: OrderType) : UserOrder(orderType)
    class UserName(orderType: OrderType) : UserOrder(orderType)
}