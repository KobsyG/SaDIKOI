package com.example.sadikoi

import android.app.Application
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.example.sadikoi.data.AppContainer
import com.example.sadikoi.data.AppDataContainer
import com.example.sadikoi.data.AppDatabase
//import com.example.sadikoi.data.UserPreferencesRepository
import com.example.sadikoi.data.UsersRepository

class SadikoiApplication : Application() {

    /**
     * AppContainer instance used by the rest of classes to obtain dependencies
     */
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(this)
    }
}

