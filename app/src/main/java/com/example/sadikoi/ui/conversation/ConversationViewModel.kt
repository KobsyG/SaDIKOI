package com.example.sadikoi.ui.conversation

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.provider.Telephony
import android.telephony.SmsManager
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sadikoi.data.IMessagesRepository
import com.example.sadikoi.data.Message
import com.example.sadikoi.data.MessagesRepository
import com.example.sadikoi.data.User
import com.example.sadikoi.data.UserUiState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import com.example.sadikoi.SadikoiApplication
import com.example.sadikoi.data.IUsersRepository
import com.example.sadikoi.data.UsersRepository
import kotlinx.coroutines.flow.collectLatest


class ConversationViewModel(
    private val messagesRepository: IMessagesRepository,
    private val usersRepository: IUsersRepository,
//    val smsManager: SmsManager //todo pour ne pas utiliser SmsManager.getDefault()
) : ViewModel() {

//    val conversationUiState: StateFlow<ConversationUiState> =
//        messagesRepository.getAllMessagesFromUser(contactId).map { ConversationUiState(it) }
//            .stateIn(
//                scope = viewModelScope,
//                started = SharingStarted.WhileSubscribed(5000),
//                initialValue = ConversationUiState()
//            )


//    private val _messages = MutableLiveData<List<Message>>() //todo use onyl StateFlow
//    val messages: LiveData<List<Message>> = _messages

//    private val _conversations = MutableLiveData<List<Message>>()
//    val conversations: LiveData<List<Message>> = _conversations

    var newMessage by mutableStateOf(MessageUi("", -1, true, 0, ""))
        private set


    private val _contactId = MutableStateFlow(-1)
    val contactId: StateFlow<Int> = _contactId.asStateFlow()

    private val _number = MutableStateFlow("")
    val number: StateFlow<String> = _number.asStateFlow()

//    fun updateNumber(number: String) {
//        _number.value = number
//    }

    fun updateContactId(contactId: Int, number: String) {
        _contactId.value = contactId
        _number.value = number
    }


    @OptIn(ExperimentalCoroutinesApi::class)
    val messages: StateFlow<List<Message>> = _contactId
        .flatMapLatest { id ->
                messagesRepository.getAllMessagesFromUser(id)
            }
            .stateIn(
                viewModelScope,
                SharingStarted.WhileSubscribed(5000),
                emptyList()
            )

    fun updateMessage(message: String) {
        newMessage = newMessage.copy(text = message)
    }

    fun Message.toMessageUi(): MessageUi = //todo eclaté car MessageUi est similaire a Message donc c'est nimp
        MessageUi(
            text = messageText,
            contactId = contactId,
            isSent = isSent,
            timestamp = timestamp,
            number = number
        )

    fun MessageUi.toMessage(): Message = Message(
        messageText = text,
        contactId = contactId,
        isSent = isSent,
        timestamp = timestamp,
        number = number
    )



    val smsManager = SmsManager.getDefault()
//    val smsManager = sadi

    fun sendSMS() {


        newMessage = newMessage.copy(
            contactId = contactId.value,
            number = number.value,
            timestamp = System.currentTimeMillis()
        )
        Log.d("sendSMS", "sendSMS : $newMessage")
        Log.d("sendSMS", "toMessage : ${newMessage.toMessage()}")
        viewModelScope.launch {

            Log.d("sendSMS", "sendSMS : $newMessage")
            Log.d("sendSMS", "toMessage : ${newMessage.toMessage()}")

            messagesRepository.insertMessage(newMessage.toMessage())
            smsManager.sendTextMessage(newMessage.number, null, newMessage.text, null, null)
            updateMessage("")
        }


    }
//    fun loadMessagesFromContact(contactId: Int) {
//        viewModelScope.launch {
//            messagesRepository.getAllMessagesFromUser(contactId).collect { messagesList ->
//                _messages.value = messagesList
//            }
//        }
//    }

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
    val contactId: Int,
//    val sender: User, //todo User
    val isSent: Boolean,
//    val receiver: User,
    val timestamp: Long,
    val number: String
)

data class ConversationUiState(val messageList: List<Message> = listOf())

data class ConvPreview(val contactId: Int, /*val number: String,*/ val lastMessage: List<Message> = listOf())

