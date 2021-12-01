package com.rastatech.projectrasta.features.home.domain.entities

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class UserEntity(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("username")
    val username: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("address")
    val address: Address? = null,
    @SerializedName("phone")
    val phone: String? = null,
    @SerializedName("website")
    val website: String? = null,
    @SerializedName("company")
    val company: Company? = null,
)

@Serializable
data class Address(
    @SerializedName("street") var street: String? = null,
    @SerializedName("suite") var suite: String? = null,
    @SerializedName("city") var city: String? = null,
    @SerializedName("zipcode") var zipcode: String? = null,
    @SerializedName("geo") var geo: Geo? = Geo()
)

@Serializable
data class Geo(
    @SerializedName("lat") var lat: String? = null,
    @SerializedName("lng") var lng: String? = null
)

@Serializable
data class Company(
    @SerializedName("name") var name: String? = null,
    @SerializedName("catchPhrase") var catchPhrase: String? = null,
    @SerializedName("bs") var bs: String? = null
)

