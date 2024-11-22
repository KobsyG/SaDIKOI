package com.example.sadikoi.ui.home

import android.annotation.SuppressLint
import android.graphics.drawable.Icon
import android.util.Log
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.sadikoi.data.Message
import com.example.sadikoi.ui.conversation.ConversationViewModel
import com.example.sadikoi.ui.theme.SaDIKOITheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter") //todo a garder?
@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel,
    onRepertoireClicked: () -> Unit,
//    onAddUserClicked: () -> Unit,
    onConvClicked: (Int, String, String) -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        containerColor = MaterialTheme.colorScheme.surface,
        floatingActionButton = {
            FloatingActionButton(
                onClick = onRepertoireClicked
            ) {
                Icon(
                    Icons.Default.Person,
                    contentDescription = "repertoire"
                )
            }
        },
//        modifier = Modifier.border(1.dp, Color.Green)
    ) {
        ListConversation(homeViewModel, onConvClicked = onConvClicked)
    }
    }

@Composable
fun ListConversation(homeViewModel: HomeViewModel,
                     listConv: List<ConversationPreview> = homeViewModel.convPreviews.collectAsState().value,
                     onConvClicked: (Int, String, String) -> Unit,
                     ) {
//    listMessage: List<Message> = conversationViewModel.convPreview.collectAsState().value.lastMessage
    LazyColumn(
        modifier = Modifier
//                .fillMaxHeight(0.8f) //todo just maxHeight ?
//            .weight(1f)
            .fillMaxWidth()
//            .border(1.dp, Color.Green)

    ) {
        items(listConv) { conv ->
            ConvPreviewButton(conv, onConvClicked)
        }
    }
}

@Composable
fun ConvPreviewButton(
    conv: ConversationPreview,
    onConvClicked: (Int, String, String) -> Unit,
//    modifier: Modifier = Modifier
//        .border(1.dp, Color.Gray)
    ) {
//    FilledTonalButton(
    Card(
        colors = CardDefaults.cardColors(
//            MaterialTheme.colorScheme.onPrimaryContainer
        ),
        modifier = Modifier,
//            .border(1.dp, Color.Gray),
//            .padding(10.dp),
        onClick = {
            onConvClicked(conv.contactId,
                conv.number,
                if (conv.contactName.isNotBlank()) conv.contactName else conv.number
                )
        },
        shape = RectangleShape,

    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
//                .border(1.dp, Color.Green)
                .padding(6.dp)
        ) {
            Text(
                text = conv.contactName,
                style = MaterialTheme.typography.titleSmall
            )
            Text(
                text = conv.lastMessage,
                style = MaterialTheme.typography.bodyMedium
                )

        }
        HorizontalDivider(
            color = Color.Gray,
            thickness = 1.dp,
//                modifier = Modifier.padding(vertical = 4.dp)
        )
    }
}

//@Preview(showBackground = true)
//@Composable
//fun HomeScreenPreview() {
//    SaDIKOITheme {
//        HomeScreen(onRepertoireClicked = {}, onAddUserClicked = {})
//    }
//}