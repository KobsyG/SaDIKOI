package com.example.sadikoi.ui.topBar

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sadikoi.data.UserPreferencesRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import com.example.sadikoi.data.User

class TopBarViewModel(private val userPreferencesRepository: UserPreferencesRepository) : ViewModel() {

    val topBarColor: StateFlow<Color> = userPreferencesRepository.topBarColor
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = Color(0xFF1976D2)
        )

    val topBarColorOption: StateFlow<ColorOption> = userPreferencesRepository.topBarColorOption.map { ColorOption.entries[it] }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = ColorOption.Primary
        )
    
    var topBarTitle by mutableStateOf("")
        private set

    fun updateTopBarTitle(title: String) {
        topBarTitle = title
        Log.d("topBarVM", "topBarTitle: $topBarTitle")
    }

    fun selectColorOption(colorOption: Int) {
        viewModelScope.launch {
            userPreferencesRepository.saveTopBarColorOption(colorOption)
        }
    }

    fun selectColor(color: Color) {
        viewModelScope.launch {
            Log.d("MainActivity", "ViewModel Selected color: $color")
            userPreferencesRepository.saveTopBarColorPreference(color)
        }
    }

}

enum class ColorOption {
    Primary,
    Surface,
    SurfaceVariant
}

