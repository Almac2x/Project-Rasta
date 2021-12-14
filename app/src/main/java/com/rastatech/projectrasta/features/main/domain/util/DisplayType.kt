package com.rastatech.projectrasta.features.main.domain.util

sealed class DisplayType(
    val value : String = ""
) {

    object ReadOnly: DisplayType(
         value = "READONLY"
    ){

    }

    object Editable: DisplayType(
        value = "EDITABLE"
    ){

    }

    // Maduga ito na converter , dapat may sariling converter
    object ToDisplayType : DisplayType(){

        fun toDisplayType(toConvert : String): DisplayType?{

            return when(toConvert.toBoolean()){
                ShowType.Open.value -> ReadOnly
                ShowType.Close.value-> Editable
                else -> null
            }

        }
    }


}