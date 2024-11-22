package com.example.sadikoi

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.provider.Telephony
import android.util.Log
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.sadikoi.data.AppContainer
import com.example.sadikoi.data.AppDatabase
import com.example.sadikoi.data.IUsersRepository
import com.example.sadikoi.data.Message
import com.example.sadikoi.data.MessagesRepository
import com.example.sadikoi.data.User
import com.example.sadikoi.data.UserPreferencesRepository
import com.example.sadikoi.data.UsersRepository
import com.example.sadikoi.ui.conversation.MessageUi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SmsReceiver() : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        Log.d("sms", "omgomgomgomgogmo: ${intent.action}")
        if (Telephony.Sms.Intents.SMS_RECEIVED_ACTION == intent.action) {
            val smsMessages = Telephony.Sms.Intents.getMessagesFromIntent(intent)
//            val messagesRepository = SadikoiApplication().container.messagesRepository
            val appContext = context.applicationContext as SadikoiApplication
            val messagesRepository = appContext.container.messagesRepository
            val userRepository = appContext.container.usersRepository
            Log.d("sms", "sms in intent.action : $smsMessages")

            for (message in smsMessages) {
                val messageBody = message.messageBody
                val sender = message.originatingAddress
                Log.d("MainActivity", "Sms received: $messageBody from $sender")
                CoroutineScope(Dispatchers.IO).launch {
                    val user = userRepository.getUserByNumber(sender.toString())
                    if (user == null) {
                       userRepository.insertUser(User(firstName = "", lastName = "", number = sender.toString(), mail = "", passion = "", photoPath = "")) //todo le toString oblitere le sender?
                        val newUser = userRepository.getUserByNumber(sender.toString())

                        if (newUser != null) {
                            val newMessage = Message(
                                messageText = messageBody,
                                contactId = newUser.id,
                                number = newUser.number,
//                        contactId = 1, //todo get contact id from sender = from originating address
                                timestamp = System.currentTimeMillis(),
                                isSent = false
                            )

                            Log.d(
                                "MainActivity",
                                "Sms received in coroutine: $messageBody from $sender"
                            )
                            messagesRepository.insertMessage(newMessage)
                        }
                        else { Log.d("MainActivity", "user is null -> problem lors de la creation") } //todo ?

                    } else {

                        val newMessage = Message(
                            messageText = messageBody,
                            contactId = user.id,
                            number = user.number,
//                        contactId = 1, //todo get contact id from sender = from originating address
                            timestamp = System.currentTimeMillis(),
                            isSent = false
                        )
                        Log.d(
                            "MainActivity",
                            "Sms received in coroutine: $messageBody from $sender"
                        )
                        messagesRepository.insertMessage(newMessage)
//                    database.messageDao().insert(newMessage)
                    }
                }
//                viewModel.onSmsReceived(messageUi) //todo getDatabase and use Dao to update DB so viewmodel can see update
                //todo see if i choose shared preference instead
            }
        }
    }
}

fun CreationExtras.sadikoiApplication(): SadikoiApplication =
    (this[AndroidViewModelFactory.APPLICATION_KEY] as SadikoiApplication)
