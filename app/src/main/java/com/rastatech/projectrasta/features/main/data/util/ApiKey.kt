package com.rastatech.projectrasta.features.main.data.util


sealed class ApiKey{

    object UserID: ApiKey(){
        const val value = "user_id"
    }

    object VoteType: ApiKey (){
        const val value = "vote_type"
    }
    object Amount : ApiKey(){
        const val value = "amount"
    }
    object WishID : ApiKey(){
        const val value = "wish_id"
    }
    object WishName: ApiKey(){
        const val value = "wish_name"
    }
    object RastaGemsRequired: ApiKey(){
        const val value = "rastagems_required"
    }
    object Authorization: ApiKey(){
        const val value = "Authorization"
    }
    object Description: ApiKey(){
        const val value = "description"
    }
    object ImageURL: ApiKey(){
        const val value = "image_url"
    }
    object Bearer: ApiKey(){
        const val value = "Bearer "
    }


}
