package com.example.sadikoi

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.sadikoi.data.UserUiState
import com.example.sadikoi.ui.HomeScreen
import com.example.sadikoi.ui.RepertoireScreen
import com.example.sadikoi.ui.UserScreen
import com.example.sadikoi.ui.UserViewModel
import com.example.sadikoi.ui.theme.SaDIKOITheme

enum class SadikoiScreen() {
    Home,
    User,
    UserAdd, //todo j'aime pas, a voir
    Repertoire
}

@Composable
fun SadikoiApp(
    viewModel: UserViewModel = viewModel(),//todo ViewModel
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
                    listOf(UserUiState("jean", "michel"), UserUiState("michel", "jean")),
                    onUserClicked = {
                        viewModel.setUserToShow(it)
                        navController.navigate(SadikoiScreen.User.name)
                    },
                    modifier = Modifier
                )
            }
            composable(route = SadikoiScreen.User.name) {
                UserScreen(
//                    user = UserUiState, todo viewModel.getUser ?
                    user = viewModel.uiState.collectAsState().value, //todo a degager grace a ouioui
                    modifier = Modifier
                    )
            }

            composable(route = SadikoiScreen.UserAdd.name) {
                UserScreen(
                    toAddUser = true,
                    modifier = Modifier
                )
            }
        }


    }
}

@Composable
fun SadikoiAppBar(
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth()
    ) {
        Text("Logo") //todo
        Text("SaDIKOI")
        Text("Menu")
    }
}

@Preview(showBackground = true)
@Composable
fun SadikoiAppPreview(){
    SaDIKOITheme {
        SadikoiApp()
    }
}