package com.rastatech.projectrasta.features.main.presentation.screens.bottom_bar

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rastatech.projectrasta.core.remote.api.RetrofitInstance
import com.rastatech.projectrasta.features.main.data.remote.api.AMOUNT_KEY
import com.rastatech.projectrasta.features.main.data.remote.api.RASTAGEMS_REQUIRED_KEY
import com.rastatech.projectrasta.features.main.data.remote.dto.CreateWishResponseDTO
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject


const val TAG ="MainViewModel"
@HiltViewModel
class MainViewModel @Inject constructor(
    private val state : SavedStateHandle

    // add here the userCases for this feature
):ViewModel(){

    init {
        var userToken = state.get<String>("access_token")
        Log.i(TAG, "UserToken: "+ userToken.toString())

        // Test lang
        viewModelScope.launch {
            Log.i(TAG, "UserTokenCouroutine: "+ userToken.toString())

            userToken  = "Bearer $userToken"
           /* val request =  async{RetrofitInstance.mainApi.getOwnProfile(userToken?:"")}
            val request2 = async { RetrofitInstance.wishApi.getHomeScreenWishes(userToken?:"") }
            val request3 = async { RetrofitInstance.wishApi.createWish(
                                                            token = userToken?:"",
                                                            createWish = CreateWishResponseDTO(
                                                                description = "SAME",
                                                                rastagems_required = 9,
                                                                wish_name = "Boss",
                                                                image_url = "URL"

                                                            )
                                                           ) }
            val request4 = async { RetrofitInstance.wishApi.likeWish(
                                                token = userToken?:"",
                                                wishID = 2
                                            ) }
            val request5 = async { RetrofitInstance.wishApi.getWish(
                                                token = userToken?:"",
                                                wishID = 2
            ) }
            val request6 = async { RetrofitInstance.wishApi.voteWish(
                token = userToken?:"",
                wishID = 2,
                voteType = mapOf("vote_type" to "DOWNVOTE")

            ) }
            val request7 = async { RetrofitInstance.mainApi.getUserBalance(
                    token = userToken?:"",
            ) }

            val request8 = async { RetrofitInstance.mainApi.deleteWish(
                token = userToken?:"",
                wishID = 1,
            ) }



           Log.i(TAG,request.await().avatar.toString())
           Log.i(TAG,request2.await().toString())
           Log.i(TAG,request3.await().wish_id.toString())

            Log.i(TAG, request7.await()[RASTAGEMS_REQUIRED_KEY].toString())
            */

            val request9 = async { RetrofitInstance.wishApi.donateToAWish(
                token = userToken?:"",
                wishID = 3,
                amount = mapOf("$AMOUNT_KEY" to 5)
            ) }


        }
    }



}