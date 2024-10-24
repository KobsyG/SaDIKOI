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
import com.example.sadikoi.data.User

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
    viewModel: RepertoireViewModel,
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


//@Preview(showBackground = true)
//@Composable
//fun RepertoireScreenPreview() {
//    SaDIKOITheme {
//        val users: List<UserUiState> = listOf(UserUiState("jean", "michel"), UserUiState("michel", "jean"))
//        RepertoireScreen(users, {})
//    }
//}