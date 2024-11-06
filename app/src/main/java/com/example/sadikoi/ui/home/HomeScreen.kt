package com.example.sadikoi.ui.home

import android.util.Log
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.sadikoi.data.Message
import com.example.sadikoi.ui.conversation.ConversationViewModel
import com.example.sadikoi.ui.theme.SaDIKOITheme

@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel,
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
        ListConversation(homeViewModel)
//        Column(
//            modifier = Modifier
////                .fillMaxHeight(0.8f) //todo just maxHeight ?
//                .weight(1f)
//                .fillMaxWidth()
//                .border(1.dp, Color.Green)
//        ) {
//            //todo List of Conversation
//            Text("List of Conversation")
////            listOf(
////                 todo
////            )
//        }
        Button( //todo floatingActionButton
            onClick = onAddUserClicked // todo add a user

        ) {
            Text("Add a User")
        }

    }
}

@Composable
fun ListConversation(homeViewModel: HomeViewModel,
                     listConv: List<ConversationPreview> = homeViewModel.convPreviews.collectAsState().value ) {
//    listMessage: List<Message> = conversationViewModel.convPreview.collectAsState().value.lastMessage
    Column(
        modifier = Modifier
                .fillMaxHeight(0.8f) //todo just maxHeight ?
//            .weight(1f)
            .fillMaxWidth()
            .border(1.dp, Color.Green)

    ) {
       listConv.map { conv ->
          ConvPreviewButton(conv)
       }
//        listOf(
//            conversationViewModel.conversations.value
//        )
    }
}

@Composable
fun ConvPreviewButton(conv: ConversationPreview) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, Color.Green)
    ) {
        Text(conv.contactName)
        Text(conv.lastMessage)
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    SaDIKOITheme {
//        HomeScreen(onRepertoireClicked = {}, onAddUserClicked = {})
    }
}