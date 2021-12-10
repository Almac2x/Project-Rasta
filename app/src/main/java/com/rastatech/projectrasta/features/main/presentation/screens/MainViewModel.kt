package com.rastatech.projectrasta.features.main.presentation.screens

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


const val TAG ="MainViewModel"
@HiltViewModel
class MainViewModel @Inject constructor(
    state : SavedStateHandle
    // add here the userCases for this feature
):ViewModel(){

    private val  _userToken = state.get<String>("access_token")
    val userToken : String
        get() = _userToken.toString()

    init {
        Log.i(TAG, "UserToken: "+ _userToken.toString())

        // Test lang
        viewModelScope.launch {
            Log.i(TAG, "UserTokenCoroutine: "+ _userToken.toString())

            //_userToken  = "Bearer $_userToken"
           /* val request =  async{RetrofitInstance.mainApi.getOwnProfile(userToken?:"")}
            val request2 = async { RetrofitInstance.wishApi.getHomeScreenWishes(userToken?:"") }
            val request3 = async { RetrofitInstance.wishApi.createWish(
                                                            token = userToken?:"",
                                                            createWish = CreateWishRequestDTO(
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
                voteType = mapOf("vote_type" to "DownVote")

            ) }
            val request7 = async { RetrofitInstance.mainApi.GetUserBalance(
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

            /*val request9 = async { RetrofitInstance.wishApi.donateToAWish(
                token = userToken?:"",
                wishID = 3,
                amount = mapOf("$AMOUNT_KEY" to 5)
            ) }*/


        }
    }



}