package com.rastatech.projectrasta.features.main.data.remote.dto

data class WishDTO(

    val description: String,
    val image_url: String,
    val rastagems_current: String,
    val rastagems_required: String,
    val wihwish_id: Int,
    val wish_name: String,
    val liked : Boolean,
    val upvotes: Int,
    val downvotes: Int,

)
