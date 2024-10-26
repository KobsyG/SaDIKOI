package com.example.sadikoi.ui.repertoire

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.sadikoi.data.UserUiState
import com.example.sadikoi.ui.theme.SaDIKOITheme
import androidx.compose.material3.Text
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.sadikoi.data.User
import com.example.sadikoi.ui.AppViewModelProvider

@Composable
fun Contact(
    user: User,
    onUserClicked: (User) -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = { //todo give userID here ?
            onUserClicked(user)
        },
        modifier = Modifier.fillMaxWidth()
    ) { Text(user.firstName) }
}

@Composable
fun UserList(
    userList: List<User>,
    onUserClicked: (User) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        userList.map { user ->
            Contact(user, onUserClicked)
    }
    }
}

@Composable
fun RepertoireScreen(
//    viewModel: RepertoireViewModel,
    userList: List<User>,
    onUserClicked: (User) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        UserList(
            userList,
            onUserClicked
        )
        }
    }

@Preview(showBackground = true)
@Composable
fun RepertoireScreenPreview() {
    SaDIKOITheme {
        val user1 = User(firstName = "jean", lastName = "michel", number = "123456789", mail = "mail", passion = "passion")
        val user2 = User(firstName = "michel", lastName = "jean", number = "123456789", mail = "mail", passion = "passion")
        val users: List<User> = listOf(user1, user2)
//        val viewModel: RepertoireViewModel = viewModel(factory = AppViewModelProvider.Factory)
        RepertoireScreen(userList = users, onUserClicked = {})
    }
}