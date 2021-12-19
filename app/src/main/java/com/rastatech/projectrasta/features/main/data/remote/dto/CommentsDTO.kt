package com.rastatech.projectrasta.features.main.data.remote.dto

data class CommentsDTO(

    val comment: String,
    val comment_id: Int,
    val created_at: String,
    val updated_at: String,
    val user: UserDetailsDTO,
)
