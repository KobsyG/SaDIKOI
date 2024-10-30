package com.example.sadikoi.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore

private const val COLOR_PREFERENCE_NAME = "color_preference"
private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(
    name = COLOR_PREFERENCE_NAME
)

interface AppContainer {
    val usersRepository: IUsersRepository
    val userPreferencesRepository: UserPreferencesRepository
    val messagesRepository: IMessagesRepository
}

class AppDataContainer(private val context: Context) : AppContainer {

//    val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")
    
    override val usersRepository: IUsersRepository by lazy {
        UsersRepository(AppDatabase.getDatabase(context).userDao())
    }

    override val userPreferencesRepository: UserPreferencesRepository by lazy {
        UserPreferencesRepository(context.dataStore)
    }

    override val messagesRepository: IMessagesRepository by lazy {
        MessagesRepository(AppDatabase.getDatabase(context).messageDao())

    }
}