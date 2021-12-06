package com.rastatech.projectrasta.features.splash_login_signup.presentation.login


import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.rastatech.projectrasta.features.splash_login_signup.data.local.entity.UserEntity
import com.rastatech.projectrasta.features.splash_login_signup.domain.use_case.UserUseCases
import com.rastatech.projectrasta.features.splash_login_signup.domain.util.OrderType
import com.rastatech.projectrasta.features.splash_login_signup.domain.util.UserOrder
import com.rastatech.projectrasta.features.splash_login_signup.presentation.util.UserState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.toList
import javax.inject.Inject


const val TAG = "LoginViewModel"
@HiltViewModel
class LoginViewModel@Inject constructor(

    private val userUseCases: UserUseCases

):ViewModel(){

    private val _state = mutableStateOf<UserState>(UserState())
    val state: State<UserState> = _state

    private var recentlyDeletedUser: UserEntity? = null // this is for restoring the recently deleted user in the login screen

    private var getUserJob: Job? = null //keeps track of the flow Job

    init {
        getUsers(UserOrder.FirstName(OrderType.Ascending)) // initialize the list with this UserOrder at initialization
    }

    /**
     * Here is where we add the events we created to call the useCases inside when
     *
     * @param event
     */

    fun onEvent(event : LoginEvents){

        when(event){

                is LoginEvents.Login ->{

                    viewModelScope.launch{
                        userUseCases.getLoginTokenApiRequest(
                            username = "cots1223", // add here the Text Fields For UserName
                            password = "Cots" // add here the TextFields for Password
                        )
                    }

                }

                is LoginEvents.Order ->{

                    //Checks if the User order is the same as the User order we want to change it to AND same with the orderType if it is the same
                    // their is a ::class to check if the classes being compared are the same
                    if (state.value.userOrder::class == event.userOrder::class
                        && state.value.userOrder.orderType == event.userOrder.orderType){
                        return
                    }

                    getUsers(userOrder = event.userOrder)


                }
                is LoginEvents.DeleteLogin ->{

                    viewModelScope.launch {
                        userUseCases.deleteUser(event.user) // uses the delete user UseCase to delete user from database
                        recentlyDeletedUser = event.user
                    }

                }
                // Restores the recently deleted user back into the database
                is LoginEvents.RestoreLogin ->{




                    viewModelScope.launch {

                        userUseCases.addUser(recentlyDeletedUser ?: return@launch) // returns a launch if the recentlyDeletedUser is Empty
                        recentlyDeletedUser = null
                    }

                }
                is LoginEvents.ToggleOrderSection ->{

                    _state.value = state.value.copy(  // makes a copy then change the value inside the state
                        isOrderSectionVisible = !state.value.isOrderSectionVisible
                    )

                }

        }
    }


    private fun getUsers(userOrder: UserOrder){

        getUserJob?.cancel() // cancels the job to prevent a new instance of Flow each time this function is called

         getUserJob = userUseCases.getAllUsers(userOrder = userOrder) // will return a Flow from getAllUsers UseCase
            .onEach { users ->

                _state.value = state.value.copy(
                    users = users,
                    userOrder = userOrder
                )

            }
            .launchIn(viewModelScope)
    }

}