package com.example.sadikoi.ui.home

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.sadikoi.ui.theme.SaDIKOITheme

@Composable
fun HomeScreen(
    onRepertoireClicked: () -> Unit,
    onAddUserClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        Button(
            onClick = onRepertoireClicked, //todo go to userListScreen
//            modifier = modifier,
        ) {
            Text("Contacts")
        }
        Column(
            modifier = Modifier
                .fillMaxHeight(0.8f) //todo just maxHeight ?
                .fillMaxWidth()
                .border(1.dp, Color.Green)
        ) {
            //todo List of Conversation
            Text("List of Conversation")
        }
        Button( //todo floatingActionButton
            onClick = onAddUserClicked // todo add a user

        ) {
            Text("Add a User")
        }

    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    SaDIKOITheme {
        HomeScreen(onRepertoireClicked = {}, onAddUserClicked = {})
    }
}