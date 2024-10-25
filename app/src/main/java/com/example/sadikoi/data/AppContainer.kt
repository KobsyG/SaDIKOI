package com.example.sadikoi.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore

interface AppContainer {
    val usersRepository: IUsersRepository
}

class AppDataContainer(private val context: Context) : AppContainer {

//    val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")
    
    override val usersRepository: IUsersRepository by lazy {
        UsersRepository(AppDatabase.getDatabase(context).userDao())
    }
}