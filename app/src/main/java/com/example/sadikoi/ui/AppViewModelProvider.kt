package com.example.sadikoi.ui

import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.sadikoi.SadikoiApplication
import com.example.sadikoi.ui.conversation.ConversationViewModel
import com.example.sadikoi.ui.repertoire.RepertoireViewModel
import com.example.sadikoi.ui.topBar.TopBarViewModel
import com.example.sadikoi.ui.user.UserAddViewModel
import com.example.sadikoi.ui.user.UserViewModel


object AppViewModelProvider {
    val Factory = viewModelFactory {
        initializer {
            UserAddViewModel(sadikoiApplication().container.usersRepository)
        }

        initializer {
            RepertoireViewModel(sadikoiApplication().container.usersRepository)
        }

        initializer {
            UserViewModel(sadikoiApplication().container.usersRepository)
        }

        initializer {
            TopBarViewModel(sadikoiApplication().container.userPreferencesRepository)
        }

        initializer {
            ConversationViewModel(sadikoiApplication().container.messagesRepository)
        }
    }
}

fun CreationExtras.sadikoiApplication(): SadikoiApplication =
    (this[AndroidViewModelFactory.APPLICATION_KEY] as SadikoiApplication)