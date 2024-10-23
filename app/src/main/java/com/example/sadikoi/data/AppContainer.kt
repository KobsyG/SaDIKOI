package com.example.sadikoi.data

import android.content.Context

interface AppContainer {
    val usersRepository: IUsersRepository
}

class AppDataContainer(private val context: Context) : AppContainer {
    /**
     * Implementation for [ItemsRepository]
     */
    override val usersRepository: IUsersRepository by lazy {
        UsersRepository(AppDatabase.getDatabase(context).userDao())
    }
}