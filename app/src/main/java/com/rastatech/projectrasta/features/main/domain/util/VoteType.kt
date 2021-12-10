package com.rastatech.projectrasta.features.main.domain.util

sealed class VoteType(
    val value:String
){

    object UpVote: VoteType(
        value = "UpVote"
    )
    object DownVote: VoteType(
        value = "DownVote"
    )
    object None: VoteType(
        value =""
    )
}
