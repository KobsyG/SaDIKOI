package com.example.sadikoi.ui.conversation

import android.content.pm.PackageManager
import android.util.Log
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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

@Composable
fun ConversationScreen(
    conversationViewModel: ConversationViewModel,
//    contactId: Int
) {
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
            .border(1.dp, Color.Green)
        )
        InputBar(
            conversationViewModel,
//            modifier = Modifier
//            .fillMaxHeight(0.2f)
//            .weight(1f)
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
    Column(
        verticalArrangement = Arrangement.Bottom,
        modifier = modifier
//            .fillMaxHeight(0.8f) //todo just maxHeight ?
//            .fillMaxWidth()
//            .border(1.dp, Color.Green)
    ) {
        MessageList(conversationViewModel)
    }
}

@Composable
fun Message(
    message: Message,
//    modifier: Modifier = Modifier
//        .fillMaxWidth()
//        .border(1.dp, Color.Green)
) {
    Row(
        horizontalArrangement =
            if (message.isSent)
                Arrangement.End
            else
                Arrangement.Start
        ,
        modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, Color.Green)
    ){
        Text(text = message.messageText)

    }
}

@Composable
fun MessageList(
    conversationViewModel: ConversationViewModel,
//    modifier: Modifier = Modifier.fillMaxWidth(),
) {
    Log.d("ConversationScreen", "ContactId : ${conversationViewModel.contactId.collectAsState().value}")
//    val messages by conversationViewModel.messages.value
    Log.d("ConversationScreen", "Messages : ${conversationViewModel.messages.collectAsState().value}")

       val messageList = conversationViewModel.messages.collectAsState().value

    Log.d("ConversationScreen", "MessageList : ${messageList}")

    for (message in messageList) {
        Message(message)
    }

//        Message("Hello", "Jean", "moi", 17545454),
//        Message("Hello", "Jean", "moi", 17545454),
//        Message("Hello", "moi", "Jean", 17545454),
//        Message("Hello", "Jean", "moi", 17545454)

}

@Composable
fun InputBar(
    viewModel: ConversationViewModel,
    message: MessageUi = viewModel.newMessage,
    onValueChange: (String) -> Unit = { viewModel.updateMessage(it) },
    modifier: Modifier = Modifier
        .border(1.dp, Color.Blue)
) {
//    val sendSmsPermission = Manifest


    Row(
        modifier = modifier
    ) {
        TextField(
            value = message.text,
            onValueChange = {
                onValueChange(it)
//                if (ContextCompat.checkSelfPermission(context, sendSmsPermission) != PackageManager.PERMISSION_GRANTED) { //todo check Airplace mode genre ?
//                    ActivityCompat.requestPermissions(activity, arrayOf(sendSmsPermission), REQUEST_CODE_SMS_PERMISSION)
//                } else {
//                    viewModel.sendSMS("+33661696704", "Hello, this is a test message!")
//                }
            }
        )
        Button(
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

//@Preview(showBackground = true)
//@Composable
//fun ConversationScreenPreview() {
//    SaDIKOITheme {
//        ConversationScreen()
//    }
//}
