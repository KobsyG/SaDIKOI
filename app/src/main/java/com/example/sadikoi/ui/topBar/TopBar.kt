package com.example.sadikoi.ui.topBar

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.sadikoi.R
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.graphics.Color.Companion.Blue
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.sadikoi.SadikoiScreen
import com.example.sadikoi.ui.AppViewModelProvider

private const val TAG = "MainActivity"

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar( //todo pour ce TopBar finalement ? TopAppBar direct ?
    viewModel: TopBarViewModel = viewModel(factory = AppViewModelProvider.Factory),
    onLanguageButtonClicked: () -> Unit,
    navController: NavHostController, //todo a tej c'est juste pour un test
    currentScreen: SadikoiScreen,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
        .height(40.dp)
        .border(1.dp, Color.Blue)
) {
    val color by viewModel.topBarColor.collectAsState()
    val colorOption by viewModel.topBarColorOption.collectAsState()

    val selectedColor = when (colorOption) {
        ColorOption.Primary -> MaterialTheme.colorScheme.primary
        ColorOption.Surface -> MaterialTheme.colorScheme.surface
        ColorOption.SurfaceVariant -> MaterialTheme.colorScheme.surfaceVariant
    }

    val onSelectedColor = when (colorOption) {
        ColorOption.Primary -> MaterialTheme.colorScheme.onPrimary
        ColorOption.Surface -> MaterialTheme.colorScheme.onSurface
        ColorOption.SurfaceVariant -> MaterialTheme.colorScheme.onSurfaceVariant
    }

    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = selectedColor,
            titleContentColor = onSelectedColor
        ),
        title = {
            Text(
                text = if (viewModel.topBarTitle.isBlank()) stringResource(currentScreen.title) else viewModel.topBarTitle
            )
        },
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "Localized description"
                    )
                }
            } else {

                IconButton(onClick = {
                    Log.d("TopBar", "navController.previousBackStackEntry : ${navController.previousBackStackEntry}")
                    Log.d("TopBar", "canNavigateBack $canNavigateBack")
                }) {
                    Image(

                        painter = painterResource(id = R.drawable.logo),
                        contentDescription = "logo42",
                        modifier = Modifier
//                      .fillMaxHeight()
//                      .aspectRatio(1f)
                        .sizeIn(maxHeight = 56.dp)
                    )
                }
            }
        },
        actions = {
//            AppBarMenu()
            ColorMenu(viewModel)
            TextButton(
                onClick = {
                    Log.d("TopBar", "onLanguageButtonClicked: $currentScreen")
                    Log.d("TopBar", "onLanguageButtonClicked: ${currentScreen != SadikoiScreen.Language}")
                    if (currentScreen != SadikoiScreen.Language) {
                        onLanguageButtonClicked()
                    }
                }
            ) {
                Icon(painter = painterResource(id = R.drawable.language_icon),
                    contentDescription = "Localized description")
            }
        }
    )
}

@Composable
fun ColorMenu(
    viewModel: TopBarViewModel = viewModel(factory = AppViewModelProvider.Factory),
    modifier: Modifier = Modifier.fillMaxWidth()
) {
    var colorExpanded by remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope() //todo probably pas la car viewModel

    Box {
        TextButton(
            onClick = { colorExpanded = !colorExpanded },
//            shape = androidx.compose.material3.MaterialTheme.shapes.small,
        ) {
            Icon(
                painter = painterResource(id = R.drawable.palette_icon),
                contentDescription = "Localized description",
            )
        }
        DropdownMenu(
            expanded = colorExpanded,
            onDismissRequest = { colorExpanded = false }
        ) {
            DropdownMenuItem(
                onClick = {
//                    viewModel.selectColor(Color.Red) //todo get ColorScheme ?
                    viewModel.selectColorOption(1)
                },
                text = {
                    Row {
//                        Icon(Icons.Default.)
                        Icon(
                            painter = painterResource(id = R.drawable.color_icon),
                            contentDescription = "Localized description",
//                            tint = Red, //todo ???
                            tint = MaterialTheme.colorScheme.tertiary
                            )
//                        Text("test")
                    }
                }
            )
            DropdownMenuItem(
                onClick = {
//                    viewModel.selectColor(Color.Blue)
                    viewModel.selectColorOption(2)
                },
                text = {
                    Row {
                        Icon(
                            painter = painterResource(id = R.drawable.color_icon),
                            contentDescription = "Localized description",
//                            tint = Blue,
                            tint = MaterialTheme.colorScheme.tertiaryContainer
                        )
                    }
                }
            )
        }
    }
}


//@Preview(showBackground = true)
//@Composable
//fun SadikoiAppBarPreview(){
//    SaDIKOITheme {
//        TopBar()
//        Spacer(Modifier.height(200.dp))
//    }
//}