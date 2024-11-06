package com.example.sadikoi.ui.conversation

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.provider.Telephony
import android.telephony.SmsManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sadikoi.data.IMessagesRepository
import com.example.sadikoi.data.Message
import com.example.sadikoi.data.MessagesRepository
import com.example.sadikoi.data.User
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ConversationViewModel(private val messagesRepository: IMessagesRepository) : ViewModel() {

//    val conversationUiState: StateFlow<ConversationUiState> =
//        messagesRepository.getAllMessagesFromUser(contactId).map { ConversationUiState(it) }
//            .stateIn(
//                scope = viewModelScope,
//                started = SharingStarted.WhileSubscribed(5000),
//                initialValue = ConversationUiState()
//            )


    private val _messages = MutableLiveData<List<Message>>() //todo use onyl StateFlow
    val messages: LiveData<List<Message>> = _messages

//    private val _conversations = MutableLiveData<List<Message>>()
//    val conversations: LiveData<List<Message>> = _conversations




//    val smsManager = SmsManager.getDefault()
//
//    fun sendSMS(phoneNumber: String, message: String) {
//        _messages.value = _messages.value.orEmpty() + MessageUi(
//            text = message,
//            sender = "Me",
//            receiver = phoneNumber,
//            timestamp = System.currentTimeMillis()
//        )
//        smsManager.sendTextMessage(phoneNumber, null, message, null, null)
//
//
//    }
    fun loadMessagesFromContact(contactId: Int) {
        viewModelScope.launch {
            messagesRepository.getAllMessagesFromUser(contactId).collect { messagesList ->
                _messages.value = messagesList
            }
        }
    }

//    fun loadConversations() {
//        viewModelScope.launch {
//
//            messagesRepository.getAllLastMessages().collect { message ->
//                _conversations.value = message
//            }
//        }
//    }
}

data class MessageUi( //todo val isMe boolean ???
    val text: String,
    val sender: String,
//    val sender: User, //todo User
    val receiver: String,
//    val receiver: User,
    val timestamp: Long
)

data class ConversationUiState(val messageList: List<Message> = listOf())

data class ConvPreview(val contactId: Int, val lastMessage: List<Message> = listOf())

