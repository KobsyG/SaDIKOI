package com.example.sadikoi.data

import android.util.Log
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.datastore.core.DataStore
import androidx.datastore.core.IOException
import androidx.datastore.dataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map

class UserPreferencesRepository(
    private val dataStore: DataStore<Preferences>
) {
    private companion object {
        val TOPBAR_COLOR = stringPreferencesKey("topbar_color")
        const val TAG = "UserPreferencesRepo"
    }

    val topBarColor: Flow<Color> = dataStore.data
//        .catch { //todo ???
//            if ( it is IOException) {
//                Log.e(TAG, "Error reading preferences.", it)
//                emit(emptyPreferences()) //todo ??
//            } else {
//                throw it
//            }
//        }
        .map { preferences ->
            preferences[TOPBAR_COLOR]?.let { Color(it.toLong()) } ?: Color.Red //todo j'aime pas
        }


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
    suspend fun saveTopBarColorPreference(color: Color) {
        dataStore.edit { preferences ->
            preferences[TOPBAR_COLOR] = color.value.toString()
        }
    }
}