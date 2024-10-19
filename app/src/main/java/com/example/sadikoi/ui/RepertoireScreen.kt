package com.example.sadikoi.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.sadikoi.data.UserUiState
import com.example.sadikoi.ui.theme.SaDIKOITheme
import androidx.compose.material3.Text

@Composable
fun Contact(
    user: UserUiState,
    onUserClicked: (UserUiState) -> Unit,
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
fun RepertoireScreen(
    users: List<UserUiState>,
    onUserClicked: (UserUiState) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        (users.map { user ->
           Contact(user, onUserClicked)
        })
        }
    }


@Preview(showBackground = true)
@Composable
fun RepertoireScreenPreview() {
    SaDIKOITheme {
        val users: List<UserUiState> = listOf(UserUiState("jean", "michel"), UserUiState("michel", "jean"))
        RepertoireScreen(users, {})
    }
}