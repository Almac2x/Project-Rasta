package com.rastatech.projectrasta.features.main.domain.util

sealed class ShowType(
    val value: Boolean
){

    object Open: ShowType(
        value = true
    )

    object Close: ShowType(
        value = false
    )



}
