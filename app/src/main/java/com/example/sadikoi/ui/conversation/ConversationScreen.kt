package com.example.sadikoi.ui.conversation

import android.content.pm.PackageManager
import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.sadikoi.ui.theme.SaDIKOITheme
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import com.example.sadikoi.data.Message
import com.example.sadikoi.ui.user.UserDetails
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Shapes
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalConfiguration

@Composable
fun ConversationScreen(
    conversationViewModel: ConversationViewModel,
    backHandle: () -> Unit,
//    contactId: Int(
) {
    BackHandler() {
        backHandle()
    }

//    LaunchedEffect(contactId) {
//        conversationViewModel.loadMessagesFromContact(contactId)
//    }
    Column {
        Conversation(
            conversationViewModel,
            modifier = Modifier
//            .fillMaxHeight() //todo just maxHeight ?
                .fillMaxWidth()

                .weight(1f)
//            .fillMaxSize()
//                .border(1.dp, Color.Green)
        )
        InputBar(
            conversationViewModel,
            modifier = Modifier
                .fillMaxWidth()
//            .fillMaxHeight(0.2f)
//            .weight(0.2f)
//        .border(1.dp, Color.Green)
        )
    }
}

@Composable
fun Conversation(
    conversationViewModel: ConversationViewModel,
    modifier: Modifier = Modifier
//        .fillMaxHeight() //todo just maxHeight ?
//        .fillMaxWidth()
//        .fillMaxSize()
//        .border(1.dp, Color.Green)

) {

    val messageList = conversationViewModel.messages.collectAsState().value.reversed()

    LazyColumn(
        verticalArrangement = Arrangement.Bottom,
        reverseLayout = true,
        modifier = modifier
//            .fillMaxHeight()
//            .border(1.dp, Color.Green)
    ) {
        items(messageList) { message ->
            Message(
                message,
//                Modifier
//                    .padding(8.dp)
////                  .heightIn(min = 48.dp)
//                    .fillMaxWidth()
//                    .border(1.dp, Color.Green)
            )

        }
    }
}

@Composable
fun Message(
    message: Message,
//    modifier: Modifier = Modifier
    modifier: Modifier = Modifier.fillMaxWidth().padding(8.dp)
//        .fillMaxWidth()
//        .border(1.dp, Color.Green)
) {
    Row(
        horizontalArrangement = if (message.isSent)
                                    Arrangement.End
                                else
                                    Arrangement.Start,
        modifier = modifier
//            .border(1.dp, Color.Red)
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .wrapContentWidth(
                    align = if (message.isSent) Alignment.End else Alignment.Start
                )

            ,

            //todo backgroundColor en fonction de isSent
            shape = RoundedCornerShape(
                topStart = if (message.isSent) 16.dp else 0.dp,
                topEnd = if (message.isSent) 0.dp else 16.dp,
                bottomStart = 16.dp,
                bottomEnd = 16.dp
            )

        ) {
            Text(
                modifier = Modifier
                    .padding(8.dp),
                text = message.messageText
            )
        }
    }
}

@Composable
fun MessageList(
    conversationViewModel: ConversationViewModel,
//    modifier: Modifier = Modifier //ici
//        .fillMaxWidth()
//        .fillMaxHeight()

) {
       val messageList = conversationViewModel.messages.collectAsState().value

    LazyColumn(
//        verticalArrangement = Arrangement.Bottom,
//        modifier = Modifier
//            .fillMaxHeight()
//            .border(1.dp, Color.Green)
    ) {
        items(messageList) { message ->
            Message(message)
        }
    }
}

@Composable
fun InputBar(
    viewModel: ConversationViewModel,
    message: MessageUi = viewModel.newMessage,
    onValueChange: (String) -> Unit = { viewModel.updateMessage(it) },
    modifier: Modifier = Modifier
) {

    Row(
//        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Bottom,
        modifier = modifier.padding(
            start = 8.dp,
            end = 8.dp,
            top = 8.dp,
            bottom = 16.dp
        )
    ) {
        TextField(
            modifier = Modifier
                .padding(4.dp)
                .weight(4f),
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
            ),
            shape = RoundedCornerShape(16.dp),

            value = message.text,
            label = { Text("Message") },
            onValueChange = {
                onValueChange(it)
            }
        )
        Button(
            modifier = Modifier
                .height(TextFieldDefaults.MinHeight)
                .padding(4.dp)
                .weight(1f),
            enabled = message.text.isNotEmpty(),
            onClick = {
                viewModel.sendSMS()
            }
        ) {
            Icon(
                Icons.Default.Send,
                contentDescription = "Send"
                )
        }
    }
}