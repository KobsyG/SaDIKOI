package com.example.sadikoi.ui.repertoire

import android.annotation.SuppressLint
import android.graphics.drawable.Icon
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.sadikoi.data.UserUiState
import com.example.sadikoi.ui.theme.SaDIKOITheme
import androidx.compose.material3.Text
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.sadikoi.data.User
import com.example.sadikoi.ui.AppViewModelProvider
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.sadikoi.ui.user.UserPicture

@Composable
fun Contact(
    user: User,
    onUserClicked: (User) -> Unit,
    modifier: Modifier = Modifier
) {
    FilledTonalButton(
        onClick = { //todo give userID here ?
            onUserClicked(user)
        },
        modifier = Modifier.fillMaxWidth(),
        shape = RectangleShape,
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
//                .border(1.dp, Color.Green)
                .fillMaxWidth()
        ) {
            UserPicture(
                uri = user.photoPath,
                modifier = Modifier.size(40.dp),
                firstLetter = if (user.firstName.isNotBlank()) user.firstName else user.number,
                id = user.id,
//                photoBitmap = user.photoBitmap
            )
            Text(
//                textAlign = TextAlign.End,
                text = if (user.firstName.isBlank()) user.number else user.firstName
            )
        }
    }
}

@Composable
fun UserList(
    userList: List<User>,
    onUserClicked: (User) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
    ) {
        items(userList) { user ->
                Contact(user, onUserClicked)
                HorizontalDivider(
                    color = Color.Gray,
                    thickness = 1.dp
                )
            }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter") //todo a garder ?
@Composable
fun RepertoireScreen(
//    viewModel: RepertoireViewModel,
    onAddUserClicked: () -> Unit,
    userList: List<User>,
    onUserClicked: (User) -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = onAddUserClicked,

                modifier = Modifier


            ) {
                Icon(
                    Icons.Default.Add,
                    contentDescription = "add"
                )
            }
        },
        floatingActionButtonPosition = FabPosition.End

    )

    {
        Column(
            modifier = modifier
        ) {
            UserList(
                userList,
                onUserClicked
            )
        }
    }

    }

@Preview(showBackground = true,
    showSystemUi = true,
    )
@Composable
fun RepertoireScreenPreview() {
    SaDIKOITheme {
        val user1 = User(firstName = "jean", lastName = "michel", number = "123456789", mail = "mail", passion = "passion", photoPath = "")
        val user2 = User(firstName = "michel", lastName = "jean", number = "123456789", mail = "mail", passion = "passion", photoPath = "")
        val users: List<User> = listOf(user1, user2)
//        val viewModel: RepertoireViewModel = viewModel(factory = AppViewModelProvider.Factory)
        RepertoireScreen(userList = users, onUserClicked = {}, onAddUserClicked = {})
    }
}