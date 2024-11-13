package com.example.sadikoi.ui.user

import android.annotation.SuppressLint
import androidx.compose.foundation.border
import androidx.compose.foundation.content.MediaType.Companion.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Button
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.sadikoi.data.UserUiState
import com.example.sadikoi.ui.AppViewModelProvider
import com.example.sadikoi.ui.theme.SaDIKOITheme
//import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter") //todo a garder ?
@Composable
fun UserScreen(
    viewModel: UserViewModel, //todo virer le "?" utile pour la preview
    toAddUser: Boolean = false,
    onSendMessageClicked: (Int, String) -> Unit,
    onEditUserClicked: (Int) -> Unit,
    user: UserUiState = viewModel.uiState.collectAsState().value,
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = { onSendMessageClicked(user.id, user.number) }
            ) {
                Icon(
                    Icons.Default.Email,
                    contentDescription = "send Message"
                )
            }
        }
    ) {

        val coroutineScope = rememberCoroutineScope() //todo pas ici probablement

        Column(
//            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
                .border(1.dp, Color.Red)
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween, //todo comment centrer picture
                modifier = Modifier
                    .fillMaxWidth()
                    .border(1.dp, Color.Blue)
            ) {
//                Button(
//                    onClick = {
//                        coroutineScope.launch {
//                            viewModel?.deleteUser() //todo virer le "?" utile pour la preview
//                            navigateBack()
//                        }
//                    }
//                ) {
//                    Icon(
//                        Icons.Default.Delete,
//                        contentDescription = "send Message"
//                    )
//                }
                Spacer(
                    modifier = Modifier
                )
                UserPicture()
                Button(
                    onClick = {
                        onEditUserClicked(user.id)
                    }, //todo use the same screen que userAdd mais prérempli + addUser devient modifier user
                    modifier = Modifier.border(1.dp, Color.Green)
                    ) {
                    Icon(
                        Icons.Default.Edit,
                        contentDescription = "edit user"
                    )
                }
            }
            Row(
//                horizontalArrangement = Arrangement.SpaceAround,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .border(1.dp, Color.Blue)
                    .background(color = Color.LightGray)
            ) {
                Text(
                    textAlign = TextAlign.Center,
                    text = "Nom",
                    modifier = Modifier
                        .background(color = Color.Gray)
                        .padding(8.dp)
                        .fillMaxWidth(0.2f)

                )
                Text(
                    text = user.lastName,
                    modifier = Modifier
//                        .background(color = Color.LightGray)
                        .padding(8.dp)
                    )
            }
            Row(
//                horizontalArrangement = Arrangement.SpaceAround,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .border(1.dp, Color.Blue)
                    .background(color = Color.LightGray)
            ) {
                Text(
                    textAlign = TextAlign.Center,
                    text = "Prenom",
                    modifier = Modifier
                        .background(color = Color.Gray)
                        .padding(8.dp)
                        .fillMaxWidth(0.2f)
                    )

                Text(
                    text = user.firstName,
                    modifier = Modifier
                        .padding(8.dp)

                )
            }
            Row(
//                horizontalArrangement = Arrangement.SpaceAround,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .border(1.dp, Color.Blue)
                    .background(color = Color.LightGray)
            ) {
                Text(
                    textAlign = TextAlign.Center,
                    text = "Numero",
                    modifier = Modifier
                        .background(color = Color.Gray)
                        .padding(8.dp)
                        .fillMaxWidth(0.2f)
                )
                Text(
                    text = user.number.toString(),
                 modifier = Modifier
                    .padding(8.dp)
                ) //todo number String direct ?
            }
            Row(
//                horizontalArrangement = Arrangement.SpaceAround,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .border(1.dp, Color.Blue)
                    .background(color = Color.LightGray)
            ) {
                Text(
                    textAlign = TextAlign.Center,
                    text = "Mail",
                    modifier = Modifier
                        .background(color = Color.Gray)
                        .padding(8.dp)
                        .fillMaxWidth(0.2f)
                    )
                Text(
                    text = user.mail,
                    modifier = Modifier
                    .background(color = Color.LightGray)
                        .padding(8.dp)
                )
            }
            Row(
//                horizontalArrangement = Arrangement.SpaceAround,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .border(1.dp, Color.Blue)
                    .background(color = Color.LightGray)
            ) {
                Text(
                    textAlign = TextAlign.Center,
                    text = "Passion",
                    modifier = Modifier
                        .background(color = Color.Gray)
                        .padding(8.dp)
                        .fillMaxWidth(0.2f)
                    )
                Text(
                    text = user.passion,
                    modifier = Modifier
                        .background(color = Color.LightGray)
                        .padding(8.dp)
                    )
            }
//            Row(
//                horizontalArrangement = Arrangement.SpaceAround,
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(16.dp)
//                    .border(1.dp, Color.Blue)
//            ) {
////                Button(
////                    onClick = {
////                        onEditUserClicked(user.id)
////                    } //todo use the same screen que userAdd mais prérempli + addUser devient modifier user
////                ) {
////                    Text("edit User")
////                }
////                Button(
////                    onClick = {
////                        onSendMessageClicked(user.id, user.number)
////                    }
////                ) {
////                    Text("send Message")
////                }
//            }
        }
    }
}

@Composable
fun UserPicture() {
    // circulaire image
    Image(
       imageVector = Icons.Default.Person,
        contentDescription = "User picture",
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .size(200.dp)
            .clip(CircleShape)
            .border(1.dp, Color.Blue)
            .background(Color.Gray)

    )
}

//@Preview(showBackground = true)
//@Composable
//fun UserScreenPreview(){
//    SaDIKOITheme {
//        UserScreen(navigateBack = {})
//    }
//}