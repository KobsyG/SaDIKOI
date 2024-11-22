package com.example.sadikoi.ui.preferences

import android.util.Log
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.core.os.LocaleListCompat
import java.util.Locale
import com.example.sadikoi.R

//import com.example.sadikoi.ui.topBar.updateAppLocale

@Composable
fun LanguageScreen(
    navigateBack: () -> Unit
) {
    Column(/*modifier = Modifier.fillMaxWidth()*/) {
        FilledTonalButton(
            onClick = {
                updateAppLocale("fr")
                navigateBack()
            },
            shape = RectangleShape
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth().padding(4.dp)
            ) {
                if (getCurrentLocale().equals("fr")) {
                    Icon(
                        Icons.Default.CheckCircle,
                        contentDescription = "Localized description"
                    ) //todo Icon only for selected langage
                }
                Image(
                    painter = painterResource(R.drawable.french_flag),
                    contentDescription = "Localized description",
                    modifier = Modifier.padding(horizontal = 4.dp)
                )
                Text(
                    text = "Français",
                    style = MaterialTheme.typography.labelLarge,
                    )
            }
        }

        FilledTonalButton(
            onClick = {
                updateAppLocale("en")
                navigateBack()
            },
            shape = RectangleShape
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth().padding(4.dp)
            ) {
                if (getCurrentLocale().equals("en")) {
                    Icon(
                        Icons.Default.CheckCircle,
                        contentDescription = "Localized description"
                    ) //todo Icon only for selected langage
                }
                Image(
                    painter = painterResource(R.drawable.uk_flag),
                    contentDescription = "Localized description",
                    modifier = Modifier.padding(horizontal = 4.dp)
                )
                Text(
                    text = "English",
                    style = MaterialTheme.typography.labelLarge,
                )
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




