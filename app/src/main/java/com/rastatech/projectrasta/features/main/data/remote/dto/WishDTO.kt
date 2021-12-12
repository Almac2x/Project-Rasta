package com.rastatech.projectrasta.features.main.data.remote.dto

data class WishDTO(

    val description: String,
    val image_url: String?,
    val rastagems_donated: Int,
    val rastagems_required: Int,
    val wish_id: Int,
    val wish_name: String,
    val liked : Boolean,
    val upvotes: Int,
    val downvotes: Int,
    val wish_owner_full_name: String,
    val wish_owner_username: String,
    val vote_status: String,

)
