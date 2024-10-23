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
fun MixUserScreen( //todo ?????
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
//            if (user.lastName.isNotEmpty()) {
            if (!toAddUser) {
                Text(user.lastName)
            } else {
                TextField(
                    value = "",
                    onValueChange = {}
                )
            }
        }
        Row(
            horizontalArrangement = Arrangement.SpaceAround,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .border(1.dp, Color.Blue)
        ) {
            Text("Prenom")
            if (!toAddUser) {
                Text(user.firstName)
            } else {
                TextField(
                    value = "",
                    onValueChange = {}
                )
            }
        }
        Row(
            horizontalArrangement = Arrangement.SpaceAround,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .border(1.dp, Color.Blue)
        ) {
            Text("Numero")
            if (!toAddUser) {
                Text(user.number.toString()) //todo number String direct ?
            } else {
                TextField(
                    value = "",
                    onValueChange = {}
                )
            }
        }
        Row(
            horizontalArrangement = Arrangement.SpaceAround,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .border(1.dp, Color.Blue)
        ) {
            Text("Mail")
            if (!toAddUser) {
                Text(user.mail)
            } else {
                TextField(
                    value = "",
                    onValueChange = {}
                )
            }
        }
        Row(
            horizontalArrangement = Arrangement.SpaceAround,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .border(1.dp, Color.Blue)
        ) {
            Text("Passion")
            if (!toAddUser) {
                Text(user.passion)
            } else {
                TextField(
                    value = "",
                    onValueChange = {}
                )
            }
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun SadikoiAppPreview(){
//    SaDIKOITheme {
//        UserScreen()
//    }
//}