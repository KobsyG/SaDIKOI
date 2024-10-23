package com.example.sadikoi.ui.user

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.sadikoi.SadikoiApplication
import com.example.sadikoi.data.AppContainer
import com.example.sadikoi.data.UserUiState
import com.example.sadikoi.ui.AppViewModelProvider
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

@Composable
fun UserAddScreen(
    viewModel: UserAddViewModel = viewModel(factory = AppViewModelProvider.Factory),
    user: UserUiState = viewModel.userUiState,
//    onValueChange: (UserUiState) -> Unit = viewModel::updateUiState, //todo qweeqweq
    onValueChange: (String) -> Unit = { viewModel.updateLastName(it) },
    modifier: Modifier = Modifier
) {
    val coroutineScope = rememberCoroutineScope() //todo pas ici probablement

    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxHeight(0.8f)
            .fillMaxWidth()
            .border(1.dp, Color.Red)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceAround,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .border(1.dp, Color.Blue)
        ) {
            Text("Nom")
                TextField(
                    value = user.lastName,
                    onValueChange = onValueChange
                )
        }
        Row(
            horizontalArrangement = Arrangement.SpaceAround,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .border(1.dp, Color.Blue)
        ) {
            Text("Prenom")

                TextField(
                    value = "",
                    onValueChange = {}
                )

        }
        Row(
            horizontalArrangement = Arrangement.SpaceAround,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .border(1.dp, Color.Blue)
        ) {
            Text("Numero")
                TextField(
                    value = "",
                    onValueChange = {}
                )

        }
        Row(
            horizontalArrangement = Arrangement.SpaceAround,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .border(1.dp, Color.Blue)
        ) {
            Text("Mail")
                TextField(
                    value = "",
                    onValueChange = {}
                )

        }
        Row(
            horizontalArrangement = Arrangement.SpaceAround,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .border(1.dp, Color.Blue)
        ) {
            Text("Passion")
                TextField(
                    value = "",
                    onValueChange = {}
                )

        }
        Button(
            onClick = {
                coroutineScope.launch{
                    viewModel.saveUser()
                }
            }
        ) {
            Text("Add User")
        }
    }
}