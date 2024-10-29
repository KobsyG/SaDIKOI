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
import com.example.sadikoi.data.User

class ConversationViewModel : ViewModel() {
    private val _messages = MutableLiveData<List<MessageUi>>()
    val messages: LiveData<List<MessageUi>> = _messages
    val smsManager = SmsManager.getDefault()

    fun sendSMS(phoneNumber: String, message: String) {
        _messages.value = _messages.value.orEmpty() + MessageUi(
            text = message,
            sender = "Me",
            receiver = phoneNumber,
            timestamp = System.currentTimeMillis()
        )
        smsManager.sendTextMessage(phoneNumber, null, message, null, null)


    }

    fun onSmsReceived(messageUi: MessageUi) {
        _messages.value = _messages.value.orEmpty() + messageUi
    }
}

data class MessageUi( //todo val isMe boolean ???
    val text: String,
    val sender: String,
//    val sender: User, //todo User
    val receiver: String,
//    val receiver: User,
    val timestamp: Long
)

class SmsReceiver(private val viewModel: ConversationViewModel) : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent ) {
        if (Telephony.Sms.Intents.SMS_RECEIVED_ACTION == intent.action) {
            val smsMessages = Telephony.Sms.Intents.getMessagesFromIntent(intent)
            for (message in smsMessages) {
                val messageBody = message.messageBody
                val sender = message.originatingAddress
                val messageUi = MessageUi(
                    text = messageBody,
                    sender = sender ?: "",
                    receiver = "me",
                    timestamp = System.currentTimeMillis()
                )
                viewModel.onSmsReceived(messageUi) //todo getDatabase and use Dao to update DB so viewmodel can see update
                                                //todo see if i choose shared preference instead
            }
    }
}