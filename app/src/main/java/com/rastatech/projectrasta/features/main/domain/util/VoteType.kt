package com.rastatech.projectrasta.features.main.domain.util

import androidx.compose.ui.text.toLowerCase
import java.util.*

sealed class VoteType(
    val value:String
){
    object UPVOTE: VoteType(
        value = "UPVOTE"
    ){

    }
    object DOWNVOTE: VoteType(
        value = "DOWNVOTE"
    ){



    }
    object NONE: VoteType(
        value ="NONE"
    ){


    }

    class toConvert(): VoteType(
        value = ""
    ){
        fun convert(toConvert : String) : VoteType?{

            return when(toConvert.lowercase()){

                "none" ->{
                    VoteType.NONE
                }
                "upvote" ->{
                    VoteType.UPVOTE
                }
                "downvote" ->{
                    VoteType.DOWNVOTE
                }
                else ->
                    null
            }
        }
    }


}
