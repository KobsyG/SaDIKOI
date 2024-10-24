package com.example.sadikoi

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.sadikoi.data.UserUiState
import com.example.sadikoi.ui.AppViewModelProvider
import com.example.sadikoi.ui.home.HomeScreen
import com.example.sadikoi.ui.repertoire.RepertoireScreen
import com.example.sadikoi.ui.repertoire.RepertoireViewModel
import com.example.sadikoi.ui.user.UserScreen
import com.example.sadikoi.ui.user.UserViewModel
import com.example.sadikoi.ui.theme.SaDIKOITheme
import com.example.sadikoi.ui.user.UserAddScreen
import com.example.sadikoi.ui.user.UserAddViewModel
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue

enum class SadikoiScreen() {
    Home,
    User,
    UserAdd, //todo j'aime pas, a voir
    Repertoire
}

@Composable
fun SadikoiApp(
//    viewModel: UserViewModel = viewModel(),//todo ViewModel
    viewModel: UserViewModel = viewModel(factory = AppViewModelProvider.Factory),
//    userAddViewModel: UserAddViewModel = viewModel(factory = AppViewModelProvider.Factory),
//    repertoireViewModel: RepertoireViewModel = viewModel(),
    repertoireViewModel: RepertoireViewModel = viewModel(factory = AppViewModelProvider.Factory),
    navController: NavHostController = rememberNavController()
) {
    Scaffold(
        topBar = {
            SadikoiAppBar(
                modifier = Modifier
                    .height(40.dp)
                    .border(1.dp, Color.Blue)
            )
        } //todo topBar
    ) { innerPadding ->
        //todo ouioui val uiState by viewModel.uiState.collectAsState()
        NavHost(
            navController = navController,
            startDestination = SadikoiScreen.Home.name,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(route = SadikoiScreen.Home.name) {
                HomeScreen(
                    onRepertoireClicked = {
                        //todo viewmodel
                        navController.navigate(SadikoiScreen.Repertoire.name)
                    },
                    onAddUserClicked = {
                        //todo viewmodel
                        navController.navigate(SadikoiScreen.UserAdd.name)
                    },
                    modifier = Modifier
//                        .padding(innerPadding) //todo probably not innerPadding
                        .fillMaxSize()
                        .padding(16.dp)
                        .border(1.dp, Color.Red)
                )
            }
            composable(route = SadikoiScreen.Repertoire.name) {
                RepertoireScreen(
                    repertoireViewModel,
                    repertoireViewModel.repertoireUiState.collectAsState().value.userList,
                    onUserClicked = {
                        viewModel.setUserToShow(it)
                        navController.navigate(SadikoiScreen.User.name)
                    },
                    modifier = Modifier
                )
            }
            composable(route = SadikoiScreen.User.name) {
                UserScreen(
                    viewModel,
//                    user = UserUiState, todo viewModel.getUser ?
                    user = viewModel.uiState.collectAsState().value, //todo a degager grace a ouioui,
                    navigateBack = { navController.navigateUp() },
                    modifier = Modifier
                    )
            }

            composable(route = SadikoiScreen.UserAdd.name) {
                UserAddScreen(
//                    viewModel = userAddViewModel,
                    navigateBack = { navController.navigateUp() },
                    modifier = Modifier
                )
            }
        }


    }
}

@Composable
fun SadikoiAppBar(
    modifier: Modifier = Modifier
        .height(40.dp)
        .border(1.dp, Color.Blue)
) {
    Row(
        modifier = modifier.fillMaxWidth()
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "logo42"
        )
//        Text("Logo") //todo
        Text("SaDIKOI")
//        Text("Menu")
        LanguageMenu()
    }
}

@Composable
fun AppBarMenu() {

}

//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun LanguageMenu() {
//    val options = listOf("Option 1", "Option 2", "Option 3", "Option 4", "Option 5")
//    var expanded by remember { mutableStateOf(false) }
//
//    Column() {
//        ExposedDropdownMenuBox(
//            expanded = expanded,
//            onExpandedChange = { expanded = !expanded } //todo different from https://developer.android.com/reference/kotlin/androidx/compose/material/package-summary#ExposedDropdownMenuBox(kotlin.Boolean,kotlin.Function1,androidx.compose.ui.Modifier,kotlin.Function1)
//        ) { }
//    }
//}

@Composable
fun LanguageMenu(
    modifier: Modifier = Modifier
//        .fillMaxSize()
) {
    var expanded by remember { mutableStateOf(false) }
    Box(/*modifier = Modifier.fillMaxSize()*/) {
        Button(
            onClick = { expanded = !expanded }
        ) {
            Icon(Icons.Default.MoreVert, contentDescription = "Localized description")
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            DropdownMenuItem(
                onClick = { /* Handle Option 1 click */ expanded = false },
                text = { Text("Option 1") },
            )
            DropdownMenuItem(
                onClick = { /* Handle Option 1 click */ expanded = false },
                text = { Text("Option 2") },
            )


        }
    }
}

@Composable
fun ColorMenu() {

}

@Preview(showBackground = true)
@Composable
fun SadikoiAppPreview(){
    SaDIKOITheme {
        SadikoiAppBar()
    }
}