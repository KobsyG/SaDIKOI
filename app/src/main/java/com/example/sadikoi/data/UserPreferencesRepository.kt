package com.example.sadikoi.data

import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.core.IOException
import androidx.datastore.dataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map

//class UserPreferencesRepository(
//    private val dataStore: DataStore<Preferences>
//) {
//    private companion object {
//        val LANGUAGE = stringPreferencesKey("language")
//        const val TAG = "UserPreferencesRepo"
//    }
//
//    val language: Flow<String> = dataStore.data
////        .catch { //todo ???
////            if ( it is IOException) {
////                Log.e(TAG, "Error reading preferences.", it)
////                emit(emptyPreferences()) //todo ??
////            } else {
////                throw it
////            }
////        }
//        .map { preferences ->
//        preferences[LANGUAGE] ?: "en" //todo: set default language from phone setting
//    }
//
//    suspend fun saveLanguagePreference(language: String) {
//        dataStore.edit { preferences ->
//            preferences[LANGUAGE] = language
//        }
//    }
//}