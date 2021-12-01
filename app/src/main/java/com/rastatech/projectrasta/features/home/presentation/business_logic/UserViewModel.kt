package com.rastatech.projectrasta.features.home.presentation.business_logic

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.rastatech.projectrasta.core.UiState
import com.rastatech.projectrasta.features.home.domain.di.UserServiceLocator
import com.rastatech.projectrasta.features.home.domain.entities.UserEntity
import com.rastatech.projectrasta.features.home.domain.repositories.UserRepository
import com.rastatech.projectrasta.features.home.domain.usecases.GetUsers
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class UserViewModel(
    private val getUsers: GetUsers
) : ViewModel() {

    private val _userState = MutableStateFlow<UiState>(UiState.Uninitialized)
    val userState: StateFlow<UiState> = _userState

    private val _users = MutableStateFlow<List<UserEntity>>(emptyList())
    val users = _users.value

    fun getUsers() {
        viewModelScope.launch(CoroutineExceptionHandler { _, throwable ->
            viewModelScope.launch {
                _userState.emit(UiState.Fail(throwable))
            }
        }) {
            _userState.emit(UiState.Loading)
            _users.emit(getUsers.invoke())
            _userState.emit(UiState.Success(_users.value))
        }
    }

    companion object {
        fun create() = UserViewModel(
            getUsers = UserServiceLocator(UserRepository()).getUsers
        )
    }
}

@Suppress("UNCHECKED_CAST")
class UserViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return UserViewModel.create() as T
    }
}

