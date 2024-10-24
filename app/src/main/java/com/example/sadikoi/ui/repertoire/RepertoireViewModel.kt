package com.example.sadikoi.ui.repertoire

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sadikoi.data.IUsersRepository
import com.example.sadikoi.data.User
import com.example.sadikoi.data.UsersRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class RepertoireViewModel(private val usersRepository: IUsersRepository) : ViewModel() {

    val repertoireUiState: StateFlow<RepertoireUiState> =
        usersRepository.getAllUsers().map { RepertoireUiState(it) }
            .stateIn( //todo pourquoi ces merdes
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = RepertoireUiState(),

            )

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }
}

data class RepertoireUiState(val userList: List<User> = listOf())