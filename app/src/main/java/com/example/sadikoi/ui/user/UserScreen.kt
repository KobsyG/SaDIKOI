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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.sadikoi.data.UserUiState
import com.example.sadikoi.ui.AppViewModelProvider
import com.example.sadikoi.ui.theme.SaDIKOITheme
//import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

@Composable
fun UserScreen(
    viewModel: UserViewModel? = null, //todo virer le "?" utile pour la preview
    toAddUser: Boolean = false,
    onSendMessageClicked: (Int) -> Unit,
    user: UserUiState = UserUiState(),
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier
) {

    val coroutineScope = rememberCoroutineScope() //todo pas ici probablement

    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .border(1.dp, Color.Red)
    ) {
        Button(
            onClick = {
                coroutineScope.launch {
                    viewModel?.deleteUser() //todo virer le "?" utile pour la preview
                    navigateBack()
                }
            }
        ) {
            Text("delete User")
        }
        Row(
            horizontalArrangement = Arrangement.SpaceAround,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .border(1.dp, Color.Blue)
        ) {
            Text("Nom")
                Text(user.lastName)
        }
        Row(
            horizontalArrangement = Arrangement.SpaceAround,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .border(1.dp, Color.Blue)
        ) {
            Text("Prenom")

            Text(user.firstName)
        }
        Row(
            horizontalArrangement = Arrangement.SpaceAround,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .border(1.dp, Color.Blue)
        ) {
            Text("Numero")
            Text(user.number.toString()) //todo number String direct ?
        }
        Row(
            horizontalArrangement = Arrangement.SpaceAround,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .border(1.dp, Color.Blue)
        ) {
            Text("Mail")
            Text(user.mail)
        }
        Row(
            horizontalArrangement = Arrangement.SpaceAround,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .border(1.dp, Color.Blue)
        ) {
            Text("Passion")
            Text(user.passion)
        }
        Row(
            horizontalArrangement = Arrangement.SpaceAround,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .border(1.dp, Color.Blue)
        ) {
            Button(
                onClick = {} //todo use the same screen que userAdd mais prérempli + addUser devient modifier user
            ) {
                Text("edit User")
            }
            Button(
                onClick = {
                    onSendMessageClicked(user.id)
                }
            ) {
                Text("send Message")
            }
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun UserScreenPreview(){
//    SaDIKOITheme {
//        UserScreen(navigateBack = {})
//    }
//}