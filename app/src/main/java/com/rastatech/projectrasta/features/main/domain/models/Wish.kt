package com.rastatech.projectrasta.features.main.domain.models

data class Wish(

    val reason : String, // This is description in DTO for wishes
    val imageURL: String,
    val wishName : String,
    val liked : Boolean,
    val upVotes : Int,
    val downVotes: Int,
    val wishID: Int,
    val rastaGemsRequired: Int,
    val rastaGemsDonated:Int,
) {

}