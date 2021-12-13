package com.rastatech.projectrasta.features.main.domain.util

sealed class VoteType(
    val value:String
){

    object UPVOTE: VoteType(
        value = "UPVOTE"
    )
    object DOWNVOTE: VoteType(
        value = "DOWNVOTE"
    )
    object NONE: VoteType(
        value ="NONE"
    )
}
