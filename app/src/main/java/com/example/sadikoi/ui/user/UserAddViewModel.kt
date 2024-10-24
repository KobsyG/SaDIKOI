package com.example.sadikoi.ui.user

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
//import com.example.sadikoi.data.UserUiState
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

    var userAddUiState by mutableStateOf(UserAddUiState())
        private set

//    fun updateUiState(userdetail: UserUiState) {
//        userUiState = userdetail()
//    }

    fun updateUiState(userDetails: UserDetails) {
        userAddUiState = UserAddUiState(userDetails = userDetails, isEntryValid = validateInput(userDetails))
    }

    suspend fun saveUser() {

        if (validateInput()) {
            usersRepository.insertUser(userAddUiState.userDetails.toUser())
        }

//        Log.d(TAG, "onStart Called")
//        Log.d(TAG, "UserUiState: $userUiState" )
//        if (userUiState.lastName.isBlank()) {
//            return
//        }
//
//        usersRepository.insertUser(userUiState.toUser())
    }

    private fun validateInput(userDetails: UserDetails = userAddUiState.userDetails): Boolean {
        return with(userDetails) { //todo check with
            firstName.isNotBlank() && lastName.isNotBlank() && number.toString().isNotBlank()
        }
//        return  (userDetails.firstName.isNotBlank() && userDetails.lastName.isNotBlank() && userDetails.number.toString().isNotBlank())
    }

}

//fun UserUiState.toUser(): User = User(
//    firstName = firstName,
//    lastName = lastName,
//    number = number,
//    mail = mail,
//    passion = passion,
//)

fun UserDetails.toUser(): User = User(
    id = id,
    firstName = firstName,
    lastName = lastName,
    number = number,
    mail = mail,
    passion = passion
)

data class UserAddUiState(
    val userDetails: UserDetails = UserDetails(),
    val isEntryValid: Boolean = false
)

data class UserDetails(
    val id: Int = 0,
    val firstName: String = "",
    val lastName: String = "",
//    val number: Int = 0, //todo number string direct ?
    val number: String = "",
    val mail: String = "",
    val passion: String = ""
)

//todo probably User.toUserDetails