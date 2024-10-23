package com.example.sadikoi.ui.user

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.sadikoi.data.UserUiState
import com.example.sadikoi.ui.theme.SaDIKOITheme

@Composable
fun UserScreen(
    toAddUser: Boolean = false,
    user: UserUiState = UserUiState(),
    modifier: Modifier = Modifier
) {
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
    }
}

@Preview(showBackground = true)
@Composable
fun SadikoiAppPreview(){
    SaDIKOITheme {
        UserScreen()
    }
}