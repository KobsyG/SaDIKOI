package com.example.sadikoi.ui.user

import android.util.Log
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.sadikoi.SadikoiApplication
import com.example.sadikoi.data.IUsersRepository
import com.example.sadikoi.data.User
import com.example.sadikoi.data.UserUiState
import com.example.sadikoi.ui.sadikoiApplication
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

private const val TAG = "MainActivity"

class UserViewModel(private val usersRepository: IUsersRepository) : ViewModel() {

    private val _uiState = MutableStateFlow(UserUiState())
    val uiState: StateFlow<UserUiState> = _uiState.asStateFlow()

    suspend fun deleteUser() {
        Log.d(TAG, "deleteUser:  ${uiState.value}")
        val user: User = User(
            id = uiState.value.id,
            firstName = uiState.value.firstName,
            lastName = uiState.value.lastName,
            number = uiState.value.number,
            mail = uiState.value.mail,
            passion = uiState.value.passion
        )
        Log.d(TAG, "deleteUser:  $user")
        usersRepository.deleteUser(user)
    }

    fun setUserToShow(user: User) {
        _uiState.update { currentState ->
            currentState.copy(
                id = user.id,
                firstName = user.firstName,
                lastName = user.lastName,
                number = user.number,
                mail = user.mail,
                passion = user.passion,
            )
        }
    }
}

fun CreationExtras.sadikoiApplication(): SadikoiApplication =
    (this[AndroidViewModelFactory.APPLICATION_KEY] as SadikoiApplication)