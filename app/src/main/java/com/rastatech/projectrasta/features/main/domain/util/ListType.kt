package com.rastatech.projectrasta.features.main.domain.util

import com.rastatech.projectrasta.core.remote.api.RetrofitInstance
import com.rastatech.projectrasta.features.main.data.remote.dto.WishDTO
import kotlinx.coroutines.Job
import retrofit2.Response

sealed class ListType(
    val value: String = ""
) {

    object HomeScreen : ListType(
         value = "HomeScreen"

    )
    {
        suspend fun getList (token: String) : Response<List<WishDTO>>{

           return RetrofitInstance.wishApi.getHomeScreenWishes(token = token)

        }


    }

    object WishesFulfilled : ListType(
        value = "WishesFulfilled"
    )
    {
        suspend fun getList (token: String) : Response<List<WishDTO>>{

            return RetrofitInstance.wishApi.getHomeScreenWishes(token = token)

        }



    }

    object WishesActive : ListType(
         value = "WishesActive"
    )
    {
        suspend fun getList (token: String) : Response<List<WishDTO>>{

            return RetrofitInstance.wishApi.getHomeScreenWishes(token = token)
        }


    }

    //Converter From String to ListType
    object ToListType: ListType(){
        fun toListType(toConvert : String): ListType?{

            return when (toConvert){
                ListType.HomeScreen.value -> HomeScreen
                ListType.WishesFulfilled.value -> WishesFulfilled
                ListType.WishesActive.value -> WishesActive
                else -> {null}
            }

        }


    }


}