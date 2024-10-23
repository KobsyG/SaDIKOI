package com.example.sadikoi.ui

import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.sadikoi.SadikoiApplication
import com.example.sadikoi.ui.user.UserAddViewModel


object AppViewModelProvider {
    val Factory = viewModelFactory {
        initializer {
            UserAddViewModel(sadikoiApplication().container.usersRepository)
        }
    }
}

fun CreationExtras.sadikoiApplication(): SadikoiApplication =
    (this[AndroidViewModelFactory.APPLICATION_KEY] as SadikoiApplication)