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
    Column {
        Button(
            onClick = {
                updateAppLocale("fr")
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
                updateAppLocale("en")
                navigateBack()
            }
        ) {
            Row() {
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




