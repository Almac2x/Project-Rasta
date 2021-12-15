package com.rastatech.projectrasta.features.main.presentation.screens.bottom_bar.gem_page

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.common.api.internal.ApiKey
import com.rastatech.projectrasta.SecretRastaApp
import com.rastatech.projectrasta.core.remote.api.RetrofitInstance
import com.rastatech.projectrasta.utils.ValidateInput
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import javax.inject.Inject


private const val TAG = "GemPageViewModel"

@HiltViewModel
class GemPageViewModel@Inject constructor(

    state: SavedStateHandle

):ViewModel(){

    private val _userToken = SecretRastaApp.prefs?.accessToken

    private val retroFitToken = "Bearer $_userToken"

    private val _gemBalance = mutableStateOf(0)
    val gemBalance : Int
        get() = _gemBalance.value

    private val _showAddGemDialog = mutableStateOf(false)
    val showAddGemDialog: State<Boolean>
        get() = _showAddGemDialog

    private val _showSendGemDialog =   mutableStateOf(false)
    val showSendGemDialog : State<Boolean>
        get() = _showSendGemDialog

    private val _showTransactionDialog =   mutableStateOf(false)
    val showTransactionDialog : State<Boolean>
        get() = _showTransactionDialog

    private val _toastMessage = mutableStateOf("")
    val toastMessage: State<String>
        get() = _toastMessage

    private val _showToast = mutableStateOf(false)
    val showToast: State<Boolean>
        get() = _showToast

     val amount = mutableStateOf(0)


    init {

        viewModelScope.launch(Dispatchers.Main) {

            getBalance()

        }


    }


    private fun getBalance (){

        viewModelScope.launch(Dispatchers.IO) {

            val requestUserBalance = async{RetrofitInstance.mainApi.getUserBalance(token = retroFitToken)}

            _gemBalance.value = requestUserBalance.await().body()?.get("rasta_gems_balance") ?: 0

        }

    }

    fun events(event : GemPageEvents){

        when(event){
            is GemPageEvents.AddGemDialog ->{
                _showAddGemDialog.value = event.showType.value
            }
            is GemPageEvents.SendGemDialog -> {
                _showSendGemDialog.value = event.showType.value
            }

            is GemPageEvents.TransactionGemDialog -> {
                _showTransactionDialog.value = event.showType.value
            }
            is GemPageEvents.AddGems -> {

                //Add here input error handling
                //Add here Toast Successful or Failed
                val eventRequest = viewModelScope.launch(Dispatchers.IO) {
                                                                                                                                        //Issue when getting amount from text field
                    val requestAddGem= async{RetrofitInstance.mainApi.addGems(token = retroFitToken, mapOf("amount" to event.amount))}
                    requestAddGem.join()

                    when (requestAddGem.await().code()){

                        200 ->{
                            _showToast.value = true
                            _toastMessage.value = "Add Successful"
                        }
                        else ->{
                            _showToast.value = true
                            _toastMessage.value ="Something Went Wrong"
                        }
                    }

                    requestAddGem.join()
                    getBalance()
                    delay(1000L)
                }


                _showAddGemDialog.value = event.showType.value // Closes Dialog
                _showToast.value = false
                amount.value = 0

                Log.i(TAG, _showToast.value.toString())
            }
            is GemPageEvents.SendGems ->{

                //Add here input error handling
                //Add here Toast Successful or Failed
                val eventRequest = viewModelScope.launch(Dispatchers.IO) {

                                                                                                //Issue when getting amount from text field    // ID is hard coded, must get from textfield
                    val requestSendGem= async{RetrofitInstance.mainApi.sendGems(token = retroFitToken, userName = event.userName,
                                                                amount = mapOf("amount" to event.amount))}
                    requestSendGem.join()

                    when (requestSendGem.await().code()){

                        200 ->{
                            _showToast.value = true
                            _toastMessage.value = "Send Successful"
                        }
                        else ->{
                            _showToast.value = true
                            _toastMessage.value ="Something Went Wrong"
                        }
                    }

                    requestSendGem.join()
                    getBalance()
                    delay(1000L)
                }
                getBalance()

                _showSendGemDialog.value = event.showType.value // Closes Dialog
                _showToast.value = false
                amount.value = 0

                Log.i(TAG, _showToast.value.toString())

            }

            is GemPageEvents.CheckInput -> {

                _gemBalance.value = if(ValidateInput.isNumber(event.input))  event.input.toInt()
                                    else _gemBalance.value

            }
        }

    }

}