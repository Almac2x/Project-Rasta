package com.rastatech.projectrasta.features.main.data.remote.dto

data class CreateWishRequestDTO(
    val description: String,
    val image_url : String,
    val rastagems_required: Int,
    val wish_id : Int? = null,
    val wish_name: String,
){
}