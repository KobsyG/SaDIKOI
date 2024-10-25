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

//class SadikoiApplication : Application() {
////    val database: AppDatabase by lazy { AppDatabase.getDatabase(this) }
//
////    override fun onCreate() {
////        super.onCreate()
////        val userRepository: UsersRepository by lazy {
////            UsersRepository(AppDatabase.getDatabase(this).userDao())
////        }
////    }
//
//    // Lazy initialization du Repository
//    val usersRepository: UsersRepository by lazy {
//        UsersRepository(AppDatabase.getDatabase(this).userDao())
//    }
//}

//private const val LANGUAGE_PREFERENCE_NAME = "language_preference"
//private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(
//    name = LANGUAGE_PREFERENCE_NAME
//)

class SadikoiApplication : Application() {

//    lateinit var userPreferencesRepository: UserPreferencesRepository

    /**
     * AppContainer instance used by the rest of classes to obtain dependencies
     */
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(this)
//        userPreferencesRepository = UserPreferencesRepository(dataStore)

    }
}

//    interface AppContainer {
//        val itemsRepository: ItemsRepository
//    }
//
//    /**
//     * [AppContainer] implementation that provides instance of [OfflineItemsRepository]
//     */
//    class AppDataContainer(private val context: Context) : AppContainer {
//        /**
//         * Implementation for [ItemsRepository]
//         */
//        override val itemsRepository: ItemsRepository by lazy {
//            OfflineItemsRepository(InventoryDatabase.getDatabase(context).itemDao())
//        }
//    }
