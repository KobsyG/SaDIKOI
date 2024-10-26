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
import com.example.sadikoi.SadikoiApplication
import com.example.sadikoi.data.AppContainer
import com.example.sadikoi.data.UserUiState
import com.example.sadikoi.data.UsersRepository
import com.example.sadikoi.ui.AppViewModelProvider
import com.example.sadikoi.ui.theme.SaDIKOITheme
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

@Composable
fun UserAddScreen(
    //For Preview
    viewModel: UserAddViewModel? = null,
    user: UserAddUiState = viewModel?.userAddUiState ?: UserAddUiState(),
    onValueChange: (UserDetails) -> Unit = { viewModel?.updateUiState(it) },

    //For real
//    viewModel: UserAddViewModel = viewModel(factory = AppViewModelProvider.Factory),
//    user: UserAddUiState = viewModel.userAddUiState,
//    onValueChange: (UserDetails) -> Unit = { viewModel.updateUiState(it) },


    userDetail: UserDetails = user.userDetails,
//    onValueChange: (UserUiState) -> Unit = viewModel::updateUiState, //todo qweeqweq

    navigateBack: () -> Unit,
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
                TextField( //todo sizemax
                    value = userDetail.lastName,
                    onValueChange = { onValueChange(userDetail.copy(lastName = it)) }
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

                TextField( //todo sizemax
                    value = userDetail.firstName,
                    onValueChange = { onValueChange(userDetail.copy(firstName = it)) }
                )

        }
        Row(
            horizontalArrangement = Arrangement.SpaceAround,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .border(1.dp, Color.Blue)
        ) {
            Text("Numero") //todo que des number + 10 number ?
                TextField(
                    value = userDetail.number,
                    onValueChange = { onValueChange(userDetail.copy(number = it)) }
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
                    value = userDetail.mail,
                    onValueChange = { onValueChange(userDetail.copy(mail = it)) }
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
                    value = userDetail.passion,
                    onValueChange = { onValueChange(userDetail.copy(passion = it)) }
                )

        }
        Button(
            onClick = {
                coroutineScope.launch{
                    viewModel?.saveUser() //todo que faire si lastname or number or firstname isnull -- virer le "?" utile pour la Preview
                    navigateBack()

                }
            }
        ) {
            Text("Add User")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun UserAddScreenPreview(){
    SaDIKOITheme {
        UserAddScreen(navigateBack = {})
    }
}