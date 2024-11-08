package com.example.sadikoi.ui.preferences

import android.util.Log
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.core.os.LocaleListCompat
import java.util.Locale

//import com.example.sadikoi.ui.topBar.updateAppLocale

@Composable
fun LanguageScreen(
    navigateBack: () -> Unit
) {
    Log.d("qwe1", "Apploc : ${AppCompatDelegate.getApplicationLocales().toLanguageTags()}") //todo check quelle langue actuelle (au lancement) pour le CheckCircle
//    Log.d("qwe11", "Apploc : ${}")
    Column {
        Button(
            onClick = {
                updateAppLocale("fr")
//                    Log.d(TAG, "before launch $currentLanguage")
//                    coroutineScope.launch{
//                        preferencesManager.setAppLanguage("qwrnqwjroiq")
//                        Log.d(TAG, "after launch $currentLanguage")
//                    }
                navigateBack()

            }
        ) {
            Row() {
                if (getCurrentLocale().equals("fr")) {
                    Icon(
                        Icons.Default.CheckCircle,
                        contentDescription = "Localized description"
                    ) //todo Icon only for selected langage
                }
                Text("Français")
            }
        }

        Button(
            onClick = {
//                    Log.d(TAG, "before english launch ${AppCompatDelegate.getApplicationLocales()}")


                updateAppLocale("en")
                navigateBack()
            }

//                    Log.d(TAG, "before english launch ${AppCompatDelegate.getApplicationLocales()}")
//                    Log.d(TAG, "before english launch $currentLanguage")
//                    coroutineScope.launch{
//                        preferencesManager.setAppLanguage("ehouiouio")
//                        Log.d(TAG, "after english launch $currentLanguage")
//                    }
        ) {
            Row() {
//                Log.d("qwe", "Apploc : ${AppCompatDelegate.getApplicationLocales()}")
                if (getCurrentLocale().equals("en")) {
                    Icon(
                        Icons.Default.CheckCircle,
                        contentDescription = "Localized description"
                    ) //todo Icon only for selected langage
                }
                Text("English")
            }
        }
    }
}

    //import androidx.core.os.LocaleListCompat

fun getCurrentLocale(): String {

    val appLocales = AppCompatDelegate.getApplicationLocales()
    if (!appLocales.isEmpty) {
        return appLocales[0]?.language ?: Locale.getDefault().language
    }
    return Locale.getDefault().language
}

    fun updateAppLocale(languageCode: String) {
        val appLocale = LocaleListCompat.forLanguageTags(languageCode)
        AppCompatDelegate.setApplicationLocales(appLocale)
    }



