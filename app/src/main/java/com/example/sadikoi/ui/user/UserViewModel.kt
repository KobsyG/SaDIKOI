package com.example.sadikoi.ui.user

import androidx.lifecycle.ViewModel
import com.example.sadikoi.data.UserUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class UserViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(UserUiState())
    val uiState: StateFlow<UserUiState> = _uiState.asStateFlow()

    fun setUserToShow(user: UserUiState) {
        _uiState.update { currentState ->
            currentState.copy(
                firstName = user.firstName,

            )
        }
    }

//    fun setQuantity(numberCupcakes: Int) {
//        _uiState.update { currentState ->
//            currentState.copy(
//                quantity = numberCupcakes,
//                price = calculatePrice(quantity = numberCupcakes)
//            )
//        }
//    }
}