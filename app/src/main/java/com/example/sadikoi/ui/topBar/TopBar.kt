package com.example.sadikoi.ui.topBar

import android.content.Context
import android.content.res.Configuration
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.os.LocaleListCompat
import com.example.sadikoi.R
import com.example.sadikoi.ui.theme.SaDIKOITheme
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.graphics.Color.Companion.Blue
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.sadikoi.ui.AppViewModelProvider
import kotlinx.coroutines.launch

import java.util.Locale

private const val TAG = "MainActivity"

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    viewModel: TopBarViewModel = viewModel(factory = AppViewModelProvider.Factory),
    modifier: Modifier = Modifier
        .height(40.dp)
        .border(1.dp, Color.Blue)
) {
    val color by viewModel.topBarColor.collectAsState()

    TopAppBar(
        title = { Text("SaDIKOI") }, // todo get from Resourse (same name for all languages ? )
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = color,
//            containerColor = Color(0xFF000000)
        ),
        navigationIcon = {

            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "logo42",
                modifier = Modifier
//                .fillMaxHeight()
//                .aspectRatio(1f)
                  .sizeIn(maxHeight = 56.dp)
                )
//            )
//            IconButton(onClick = { }) {
//                Icon(
//                    Icons.Filled.ArrowBack,
//                    contentDescription = "Localized description"
//                )
//            }
        },
        actions = {
//            AppBarMenu()
            ColorMenu(viewModel)
        }
    )
//    Row(
//        verticalAlignment = Alignment.CenterVertically,
//        modifier = modifier
//            .fillMaxWidth()
//    ) {
//        Image(
//            painter = painterResource(id = R.drawable.logo),
//            contentDescription = "logo42"
//        )
////        Text("Logo") //todo
////        Text("SaDIKOI")
//        Text(stringResource(id = R.string.app_name))
//
//        Spacer(Modifier.weight(1f))
////        Text("Menu")
////        AppBarMenu()
//        LanguageMenu()
//    }
}

@Composable
fun AppBarMenu() {
    var menuExpanded by remember { mutableStateOf(false) }

    Box(modifier = Modifier) {
        Button(
            onClick = { menuExpanded = !menuExpanded }
        ) {
            Icon(Icons.Default.MoreVert, contentDescription = "Localized description")
        }
        DropdownMenu(
            expanded = menuExpanded,
            onDismissRequest = { menuExpanded = false }
        ) {
//            DropdownMenuItem(
//                onClick = { /* Handle Option 1 click */ menuExpanded = false },
//                text = {
//                    LanguageMenu()
//                }
//            )
            DropdownMenuItem(
                onClick = { /* Handle Option 1 click */ menuExpanded = false },
                text = {
                    ColorMenu()
                }
            )


        }
    }
}

//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun LanguageMenu() {
//    val options = listOf("Option 1", "Option 2", "Option 3", "Option 4", "Option 5")
//    var expanded by remember { mutableStateOf(false) }
//
//    Column() `
//        ExposedDropdownMenuBox(
//            expanded = expanded,
//            onExpandedChange = { expanded = !expanded } //todo different from https://developer.android.com/reference/kotlin/androidx/compose/material/package-summary#ExposedDropdownMenuBox(kotlin.Boolean,kotlin.Function1,androidx.compose.ui.Modifier,kotlin.Function1)
//        ) { }
//    }
//}

@Composable
fun LanguageMenu(
    modifier: Modifier = Modifier.fillMaxWidth()
//        .fillMaxSize()
) {

//    val appLocale: LocaleListCompat = LocaleListCompat.forLanguageTags("xx-YY")
// Call this on the main thread as it may require Activity.restart()
//    AppCompatDelegate.setApplicationLocales(appLocale)


    var languageExpanded by remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()
//    val context = LocalContext.current
//    val preferencesManager = PreferencesManager(context)
//    val currentLanguage by preferencesManager.appLanguage.collectAsState(initial = Locale.getDefault().language)

    Box( //todo Surface ???
        modifier = Modifier
    ) {
        TextButton(
            onClick = { languageExpanded = !languageExpanded },
            shape = androidx.compose.material3.MaterialTheme.shapes.small,
        ) {
            Text("Langage") //todo langage en fonction de la langue
        }
        DropdownMenu(
            expanded = languageExpanded,
            onDismissRequest = { languageExpanded = false }
        ) {
            DropdownMenuItem(
                onClick = {
                    languageExpanded = false
                    updateAppLocale("fr")
//                    Log.d(TAG, "before launch $currentLanguage")
//                    coroutineScope.launch{
//                        preferencesManager.setAppLanguage("qwrnqwjroiq")
//                        Log.d(TAG, "after launch $currentLanguage")
//                    }

                },
                text = {
                    Row() {
                        Icons.Default.CheckCircle
                        Text("Français")
                    }
                },
            )
            DropdownMenuItem(
                onClick = {
//                    Log.d(TAG, "before english launch ${AppCompatDelegate.getApplicationLocales()}")
                    languageExpanded = false


                        updateAppLocale("en")

//                    Log.d(TAG, "before english launch ${AppCompatDelegate.getApplicationLocales()}")
//                    Log.d(TAG, "before english launch $currentLanguage")
//                    coroutineScope.launch{
//                        preferencesManager.setAppLanguage("ehouiouio")
//                        Log.d(TAG, "after english launch $currentLanguage")
//                    }
                },
                text = {
                    Row() {
                        Icon(Icons.Default.CheckCircle, contentDescription = "Localized description") //todo Icon only for selected langage
                        Text("English")
                    }
                },

                )


        }
    }
}

@Composable
fun ColorMenu(
    viewModel: TopBarViewModel = viewModel(factory = AppViewModelProvider.Factory),
    modifier: Modifier = Modifier.fillMaxWidth()
) {
    var colorExpanded by remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()

    Box {
        TextButton(
            onClick = { colorExpanded = !colorExpanded },
            shape = androidx.compose.material3.MaterialTheme.shapes.small,
        ) {
            Text("Color")
        }
        DropdownMenu(
            expanded = colorExpanded,
            onDismissRequest = { colorExpanded = false }
        ) {
            DropdownMenuItem(
                onClick = {
                    viewModel.selectColor(Color.Red)
                },
                text = {
                    Row {
//                        Icon(Icons.Default.)
                        Icon(
                            painter = painterResource(id = R.drawable.red),
                            contentDescription = "Localized description",
                            tint = Red, //todo ???
                        )
//                        Text("test")
                    }
                }
            )
            DropdownMenuItem(
                onClick = {viewModel.selectColor(Color.Blue)},
                text = {
                    Row {
                        Icon(
                            painter = painterResource(id = R.drawable.blue),
                            contentDescription = "Localized description",
                            tint = Blue,
                        )
                    }
                }
            )
        }
    }
}

//import androidx.core.os.LocaleListCompat

fun updateAppLocale(languageCode: String) {
    val appLocale = LocaleListCompat.forLanguageTags(languageCode)
    AppCompatDelegate.setApplicationLocales(appLocale)
}


//fun updateLanguage(context: Context, languageCode: String) {
//    val locale = Locale(languageCode)
//    Locale.setDefault(locale)
//
//    val config = Configuration(context.resources.configuration)
//    config.setLocale(locale)
//    context.resources.updateConfiguration(config, context.resources.displayMetrics)
//}

//val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")
//
//class PreferencesManager(private val context: Context) {
//
//    private val LANGUAGE_KEY = stringPreferencesKey("language")
//
//    val appLanguage: Flow<String> = context.dataStore.data
//        .map { preferences ->
//            preferences[LANGUAGE_KEY] ?: Locale.getDefault().language
//        }
//
//    suspend fun setAppLanguage(language: String) {
//        context.dataStore.edit { preferences ->
//            preferences[LANGUAGE_KEY] = language
//        }
//    }
//}


@Preview(showBackground = true)
@Composable
fun SadikoiAppBarPreview(){
    SaDIKOITheme {
        TopBar()
        Spacer(Modifier.height(200.dp))
    }
}