package com.rastatech.projectrasta.ui.components.vote_button

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rastatech.projectrasta.SecretRastaApp
import com.rastatech.projectrasta.core.remote.api.RetrofitInstance
import com.rastatech.projectrasta.features.main.domain.util.VoteType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class VoteButtonViewModel @Inject constructor(): ViewModel() {

    private val _userToken = SecretRastaApp.prefs?.accessToken

    private val retroFitToken = "Bearer $_userToken"


    fun vote(wishID: Int , voteType: VoteType){


         viewModelScope.launch (Dispatchers.IO){

            RetrofitInstance.wishApi.voteAWish(token =  retroFitToken, wishID = wishID,
                voteType = mapOf("vote_type" to voteType.value))
         }

    }
}