package com.rastatech.projectrasta.features.main.domain.util

sealed class DisplayType {

    object ReadOnly: DisplayType()

    object Editable: DisplayType()
}