package com.rastatech.projectrasta.features.main.data.remote.dto

data class CreateWishResponseDTO(
    val description: String,
    val image_url : String,
    val rastagems_required: Int,
    val wish_id : Int? = 0,
    val wish_name: String,
){
}