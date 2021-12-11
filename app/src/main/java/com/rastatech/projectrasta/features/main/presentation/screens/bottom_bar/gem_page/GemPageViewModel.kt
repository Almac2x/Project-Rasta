package com.rastatech.projectrasta.features.main.presentation.screens.bottom_bar.gem_page

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rastatech.projectrasta.utils.ValidateInput
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
class GemPageViewModel@Inject constructor(

    state: SavedStateHandle

):ViewModel(){

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
                viewModelScope.launch(Dispatchers.IO) {


                }
            }
            is GemPageEvents.SendGems ->{

            }

            is GemPageEvents.CheckInput -> {

                _gemBalance.value = if(ValidateInput.isNumber(event.input))  event.input.toInt()
                                    else _gemBalance.value

            }
        }

    }

}