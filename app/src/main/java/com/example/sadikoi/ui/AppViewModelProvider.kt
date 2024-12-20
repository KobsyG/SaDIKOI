package com.example.sadikoi.ui

import android.content.Context
import android.telephony.SmsManager
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.sadikoi.SadikoiApplication
import com.example.sadikoi.ui.conversation.ConversationViewModel
import com.example.sadikoi.ui.home.HomeViewModel
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
            ConversationViewModel(
                sadikoiApplication().container.messagesRepository,
                sadikoiApplication().container.usersRepository,
//                ContextCompat.getSystemService(SmsManager::javaClass) //todo pour ne pas utiliser SmsManager.getDefault
//                sadikoiApplication().getSystemService(Context.TEXT_SERVICES_MANAGER_SERVICE)
                )
        }
        initializer {
            HomeViewModel(sadikoiApplication().container.messagesRepository, sadikoiApplication().container.usersRepository)
        }
    }
}

fun CreationExtras.sadikoiApplication(): SadikoiApplication =
    (this[AndroidViewModelFactory.APPLICATION_KEY] as SadikoiApplication)