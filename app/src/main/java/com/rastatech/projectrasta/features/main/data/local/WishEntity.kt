package com.rastatech.projectrasta.features.main.data.local

data class WishEntity (

    val wish_id : String,
    val wish_name : String,
    val description: String,
    val rastagems_required: Int,
    val rastagems_donated: Int,
    val user_id: String,
    val image : Int
        )

