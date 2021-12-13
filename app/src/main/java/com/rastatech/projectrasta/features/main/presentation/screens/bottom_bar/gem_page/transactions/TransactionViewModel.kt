package com.rastatech.projectrasta.features.main.presentation.screens.bottom_bar.gem_page.transactions

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rastatech.projectrasta.SecretRastaApp
import com.rastatech.projectrasta.core.remote.api.RetrofitInstance
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Copyright 2021, White Cloak Technologies, Inc., All rights reserved.
 *
 * @author ChristianLloyd
 * @since 12/10/2021
 */


const val TAG = "TransactionViewModel"
@HiltViewModel
class TransactionViewModel@Inject constructor(

):ViewModel() {

    private val _userToken = SecretRastaApp.prefs?.accessToken

    private val retroFitToken = "Bearer $_userToken"


    init {
        viewModelScope.launch (Dispatchers.IO){

            val requestHistory =  async{RetrofitInstance.mainApi.getTransactionHistory(token = retroFitToken)}
            Log.i(TAG,requestHistory.await().body()?.transaction_date.toString())

        }



    }


}