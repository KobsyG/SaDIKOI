package com.example.sadikoi.ui.topBar

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sadikoi.data.UserPreferencesRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class TopBarViewModel(private val userPreferencesRepository: UserPreferencesRepository) : ViewModel() {

    val topBarColor: StateFlow<Color> = userPreferencesRepository.topBarColor
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = Red
        )


    fun selectColor(color: Color) {
        viewModelScope.launch {
            userPreferencesRepository.saveTopBarColorPreference(color)
        }
    }

}
