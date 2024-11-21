package com.example.sadikoi.ui.user

import android.content.Context
import android.net.Uri
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
//import com.example.sadikoi.data.UserUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import com.example.sadikoi.data.IUsersRepository
import com.example.sadikoi.data.User
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

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

    fun emptyUiState() {
        userAddUiState = UserAddUiState()
    }

//    fun updateUiStateById(id: Int) {
//        Log.d("UserAddViewModel", "updateUiStateById: $id")
//        usersRepository.getUser(id).map { user ->
//            Log.d("UserAddViewModel", "updateUiStateById: $user")
//            if (user != null) {
//                Log.d("UserAddViewModel", "updateUiStateById: $user")
//                updateUiState(user.toUserDetails())
//                Log.d("UserAddViewModel", "updateUiStateById: ${userAddUiState.userDetails}")
//            }
//            }
//        }

    fun updateUiStateById(id: Int) {
        Log.d("UserAddViewModel", "updateUiStateById: $id")
        viewModelScope.launch {
            usersRepository.getUser(id).collect() { user ->
                Log.d("UserAddViewModel", "updateUiStateById: $user")
                if (user != null) {
                    Log.d("UserAddViewModel", "updateUiStateById: $user")
                    updateUiState(user.toUserDetails())
                    Log.d("UserAddViewModel", "updateUiStateById: ${userAddUiState.userDetails}")
                }
            }
        }
    }


    suspend fun saveUser() {

        if (validateInput() && userAddUiState.userDetails.id != 0) {
            usersRepository.updateUser(userAddUiState.userDetails.toUser())

        }
        else if (validateInput()) {
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

//    private fun saveImageToInternalStorage(context: Context, uri : Uri) {
//        val inputStream = context.contentResolver.openInputStream(uri)
//        val outputStream = context.openFileOutput("image.jpg", Context.MODE_PRIVATE)
//        inputStream.use { input ->
//            outputStream.use { output ->
//                input?.copyTo(output)
//            }
//        }
//    }

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

fun User.toUserDetails(): UserDetails = UserDetails(
    id = id,
    firstName = firstName,
    lastName = lastName,
    number = number,
    mail = mail,
    passion = passion,
    photoPath = photoPath
)

fun UserDetails.toUser(): User = User(
    id = id,
    firstName = firstName,
    lastName = lastName,
    number = number,
    mail = mail,
    passion = passion,
    photoPath = photoPath
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
    val passion: String = "",
    val photoPath: String = "",
)

//todo probably User.toUserDetails