package com.rastatech.projectrasta.features.main.data.remote.dto


data class WishDTO(

    val description: String,
    val image_url: String?,
    val rastagems_donated: Int,
    val rastagems_required: Int = 0,
    val wish_id: Int,
    val wish_name: String,
    val liked : Boolean,
    val upvotes: Int,
    val downvotes: Int,
    val vote_status: String,
    val user: UserDetailsDTO,
    val created_at: String,
    val updated_at: String



)
