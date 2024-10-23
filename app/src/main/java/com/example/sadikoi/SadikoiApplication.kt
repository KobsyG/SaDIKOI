package com.example.sadikoi

import android.app.Application
import com.example.sadikoi.data.AppContainer
import com.example.sadikoi.data.AppDataContainer
import com.example.sadikoi.data.AppDatabase
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
