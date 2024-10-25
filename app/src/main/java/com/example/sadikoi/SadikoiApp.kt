package com.example.sadikoi

import android.content.Context
import android.content.res.Configuration
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.sadikoi.ui.topBar.TopBar
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import java.util.Locale

private const val TAG = "MainActivity"

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
            TopBar(
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



//@Preview(showBackground = true)
//@Composable
//fun SadikoiAppPreview() {
//    SaDIKOITheme {
//        SadikoiApp()
//    }
//}