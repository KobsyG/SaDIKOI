package com.example.sadikoi.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [User::class], version = 3, exportSchema = false) //todo singleton design pattern if app run in a single process. exportSchema??
abstract class AppDatabase : RoomDatabase() { //todo probably UserDatabase

    abstract fun userDao() : UserDao

    companion object {
        @Volatile // todo Volatile useless if only one threqd ?
        private var Instance: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(
                    context,
                    AppDatabase::class.java,
                    "appDatabase")
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { Instance = it }
            }
        }
    }
}