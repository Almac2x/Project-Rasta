package com.rastatech.projectrasta.features.wish_item_page.presentation.screens

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rastatech.projectrasta.SecretRastaApp
import com.rastatech.projectrasta.core.remote.api.RetrofitInstance
import com.rastatech.projectrasta.features.main.data.remote.dto.DonatorDTO
import com.rastatech.projectrasta.features.main.domain.use_case.WishUseCases
import com.rastatech.projectrasta.features.main.domain.util.VoteType
import com.rastatech.projectrasta.nav_graph.util.NavigationKey
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import javax.inject.Inject

/**
 * Copyright 2021, White Cloak Technologies, Inc., All rights reserved.
 *
 * @author ChristianLloyd
 * @since 12/12/2021
 */

private const val TAG = "WishItemPageViewModel"
@HiltViewModel
class WishItemPageViewModel@Inject constructor(
    state: SavedStateHandle,
    private val useCases: WishUseCases
): ViewModel() {

    private val _userToken = SecretRastaApp.prefs?.accessToken

    private val retroFitToken = "Bearer $_userToken"

    private val _wishID = state.get<Int>(NavigationKey.WishID.value) ?:0
    val wishID : Int
        get() = _wishID

    private var _userID = mutableStateOf(0)
    val userID : Int
        get() = _userID.value

    private var _userRastaGems = mutableStateOf(52)
    val userRastaGems : Int
        get() = _userRastaGems.value

    private var _wishName =  mutableStateOf("Nani")
    val wishName : String
        get() = _wishName.value

    private var _wisherFullName = mutableStateOf("NaniLastName")
    val wisherFullName : String
        get() = _wisherFullName.value

    private var _wisherUserName = mutableStateOf("UserName")
    val wisherUserName : String
        get() = _wisherUserName.value

    private var _rastaGemsDonated = mutableStateOf(1)
    val rastaGemsDonated : Int
        get() = _rastaGemsDonated.value

    private var _rastaGemsRequired = mutableStateOf(5)
    val rastaGemsRequired : Int
        get() = _rastaGemsRequired.value

     var  liked  = mutableStateOf(true)


    private var _voteStatus: MutableState<VoteType> = mutableStateOf(VoteType.NONE)
    val voteStatus : VoteType
        get()  = _voteStatus.value

    //Description
    private var _reason =  mutableStateOf("")
    val reason : String
        get() = _reason.value

    private var _imageURL =   mutableStateOf("")
    val imageURL : String
        get() = _imageURL.value

    private var _numberOfUpVotes = mutableStateOf(6)
    val numberOfUpVotes : Int
        get() = _numberOfUpVotes.value

    private var _numberOfDownVotes = mutableStateOf(8)
    val numberOfDownVotes : Int
        get() = _numberOfDownVotes.value

    private var _listOfDonators = mutableStateOf(emptyList<DonatorDTO>())
    val listOfDonators : List<DonatorDTO>
        get() = _listOfDonators.value


    init {

        Log.i(TAG, " wishID = $_wishID")

        updateDetails()

    }

    private fun updateDetails(){
        viewModelScope.launch (Dispatchers.Main){

            async {getBalance()}
            async {getWishDetails()}
            async { getDonators() }

        }
    }

    private fun getDonators(){

        viewModelScope.launch(Dispatchers.IO) {

            val requestDonators = async { RetrofitInstance.wishApi.getDonators(
                                                                token = retroFitToken, wishID = wishID
            ) }

            _listOfDonators.value = requestDonators.await().body()?: emptyList<DonatorDTO>()

        }
    }


    fun onEvent (event: WishItemPageEvents){

        when(event){
            is WishItemPageEvents.Donate -> {

                viewModelScope.launch(Dispatchers.IO) {
                    val requesToDonate = async {RetrofitInstance.wishApi.donateToAWish(
                        token = retroFitToken, wishID = wishID, amount = mapOf("amount" to event.amount)
                    ) }
                }

                viewModelScope.launch (Dispatchers.Main){

                    async {getBalance()}
                    async {getWishDetails()}

                }

            }
        }


    }


    private fun getWishDetails(){

        viewModelScope.launch(Dispatchers.IO) {

            val requestWishDetails = async { RetrofitInstance.wishApi.getWish(token = retroFitToken, wishID = _wishID) }

            val wish = requestWishDetails.await().body()

            requestWishDetails.join()

            _wishName.value = wish?.wish_name.toString() // to String even if null
            _wisherFullName.value = wish?.user?.full_name.toString()
            _wisherUserName.value = wish?.user?.username.toString()
            _numberOfDownVotes.value = wish?.downvotes ?:0
            _numberOfUpVotes.value = wish?.upvotes?:0
            _reason.value = wish?.description.toString()
            _rastaGemsDonated.value= wish?.rastagems_donated ?:0
            _rastaGemsRequired.value = wish?.rastagems_required ?:0
            liked.value = wish?.liked ?:false
            _imageURL.value = wish?.image_url.toString()
            _userID.value = wish?.user?.user_id?:0
            _voteStatus.value = VoteType.toConvert().convert(wish?.vote_status.toString()) ?: VoteType.NONE
        }

    }

    private suspend fun getBalance (){

        viewModelScope.launch(Dispatchers.IO) {

            val requestUserBalance =
                async { RetrofitInstance.mainApi.getUserBalance(token = retroFitToken) }

            _userRastaGems.value = requestUserBalance.await().body()?.get("rasta_gems_balance") ?: 0

        }

    }
}

