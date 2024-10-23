package com.example.sadikoi.ui.user

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.sadikoi.data.UserUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import com.example.sadikoi.data.IUsersRepository
import com.example.sadikoi.data.User
//import com.example.sadikoi.data.UserRepository

private const val TAG = "MainActivity"

class UserAddViewModel(private val usersRepository: IUsersRepository) : ViewModel() {

//    private val _uiState = MutableStateFlow(UserUiState())
//    val uiState: StateFlow<UserUiState> = _uiState.asStateFlow()

    var userUiState by mutableStateOf(UserUiState())
        private set

//    fun updateUiState(userdetail: UserUiState) {
//        userUiState = userdetail()
//    }

    fun updateLastName(lastName: String) {
        userUiState = UserUiState(lastName = lastName)
    }

    suspend fun saveUser() {

//        Log.d(TAG, "onStart Called")
        Log.d(TAG, "UserUiState: $userUiState" )
        if (userUiState.lastName.isBlank()) {
            return
        }

        usersRepository.insertUser(userUiState.toUser())
    }

}

fun UserUiState.toUser(): User = User(
    firstName = firstName,
    lastName = lastName,
    number = number,
    mail = mail,
    passion = passion,
)

data class UserUiState(
    val firstName: String = "",
    val lastName: String = "",
    val number: Int = 0, //todo number string direct ?
    val mail: String = "",
    val passion: String = ""
)

data class UserDetails(
    val firstName: String = "",
    val lastName: String = "",
    val number: Int = 0, //todo number string direct ?
    val mail: String = "",
    val passion: String = ""
)