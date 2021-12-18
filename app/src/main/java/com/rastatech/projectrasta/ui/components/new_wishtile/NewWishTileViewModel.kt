package com.rastatech.projectrasta.ui.components.new_wishtile

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rastatech.projectrasta.SecretRastaApp
import com.rastatech.projectrasta.core.remote.api.RetrofitInstance
import com.rastatech.projectrasta.ui.components.wish_list_page.WishPageEvents
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject


private const val TAG = "NewWishTileViewmodel"
@HiltViewModel
class NewWishTileViewModel @Inject constructor(

):ViewModel() {

    private val _userToken = SecretRastaApp.prefs?.accessToken
    private val retroFitToken = "Bearer $_userToken"

    private val _toastMessage = mutableStateOf("")
    val toastMessage: State<String>
        get() = _toastMessage

    private val _showToast = mutableStateOf(false)
    val showToast: State<Boolean>
        get() = _showToast



    private fun checkResponseCode(code : Int){

        when (code){

            200 ->{
                _showToast.value = true
                _toastMessage.value = "Delete Successful"
            }
            else ->{
                _showToast.value = true
                _toastMessage.value ="Something Went Wrong"
            }
        }

        _showToast.value = false
        _toastMessage.value = ""
    }

    fun onEvent (event : WishPageEvents){

        when(event){
            is WishPageEvents.DeleteWish ->{
                val eventRequest = viewModelScope.launch(Dispatchers.IO) {

                    val requestDeleteGem=  async{
                        RetrofitInstance.wishApi.deleteAWish(
                        token = retroFitToken,
                        wishID = event.wishID)}

                    requestDeleteGem.join()

                    checkResponseCode (requestDeleteGem.await().code())
                }
            }
            is WishPageEvents.Vote ->{

                val eventRequest = viewModelScope.launch {

                    val requestVote= async{RetrofitInstance.wishApi.voteAWish(token = retroFitToken,  wishID = event.wishID,
                        voteType =  mapOf("vote_type" to event.voteType.value)

                    )}

                    requestVote.join()

                    checkResponseCode (requestVote.await().code())

                }
            }
            is WishPageEvents.LikeAWish -> {
                val eventRequest = viewModelScope.launch {

                    val requestLike = async {
                        RetrofitInstance.wishApi.likeAWish(
                            token = retroFitToken, wishID = event.wishID,

                            )
                    }

                    checkResponseCode(requestLike.await().code())

                }

            }
        }

    }

}