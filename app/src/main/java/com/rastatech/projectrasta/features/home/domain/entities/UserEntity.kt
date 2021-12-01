package com.rastatech.projectrasta.features.home.domain.entities

import com.google.gson.annotations.SerializedName

data class UserEntity(
    @SerializedName("user_name" ) var userName : String? = null,
    @SerializedName("email"     ) var email    : String? = null,
    @SerializedName("name"      ) var name     : String? = null

)
