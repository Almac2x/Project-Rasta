package com.rastatech.projectrasta.features.main.domain.util


sealed class UserType {

    object Current: UserType()
    object Other: UserType()
}