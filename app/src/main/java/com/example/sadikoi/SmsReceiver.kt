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
                    val newMessage = Message(
                        messageText = messageBody,
                        contactId = userRepository.getUserByNumber(sender.toString()),
//                        contactId = 1, //todo get contact id from sender = from originating address
                        timestamp = System.currentTimeMillis()
                    )
                    Log.d("MainActivity", "Sms received in coroutine: $messageBody from $sender")
                    messagesRepository.insertMessage(newMessage)
//                    database.messageDao().insert(newMessage)
                }
//                viewModel.onSmsReceived(messageUi) //todo getDatabase and use Dao to update DB so viewmodel can see update
                //todo see if i choose shared preference instead
            }
        }
    }
}

fun CreationExtras.sadikoiApplication(): SadikoiApplication =
    (this[AndroidViewModelFactory.APPLICATION_KEY] as SadikoiApplication)

//class SmsReceiver : BroadcastReceiver() { //todo from https://gorkemkara.net/android-sms-received-event-jetpack-compose/
//    override fun onReceive(context: Context?, intent: Intent?) {
//        if (intent?.action == "android.provider.Telephony.SMS_RECEIVED") {
//            val bundle = intent.extras
//            if (bundle != null) {
//                val pdus = bundle.get("pdus") as Array<*>
//                pdus.forEach { pdu ->
//                    val smsMessage = SmsMessage.createFromPdu(pdu as ByteArray)
//                    val messageBody = smsMessage.messageBody
//                    val sender = smsMessage.originatingAddress
//                    // Here, we could trigger a callback or an event
//                    Log.d("SmsReceiver", "Received SMS from: $sender, Message: $messageBody")
//                }
//            }
//        }
//    }
//}

//package com.example.sadikoi.data
//
//import android.content.Context
//import androidx.datastore.core.DataStore
//import androidx.datastore.preferences.core.Preferences
//import androidx.datastore.preferences.preferencesDataStore
//
//private const val COLOR_PREFERENCE_NAME = "color_preference"
//private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(
//    name = COLOR_PREFERENCE_NAME
//)
//
//interface AppContainer {
//    val usersRepository: IUsersRepository
//    val userPreferencesRepository: UserPreferencesRepository
//}
//
//class AppDataContainer(private val context: Context) : AppContainer {
//
////    val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")
//
//    override val usersRepository: IUsersRepository by lazy {
//        UsersRepository(AppDatabase.getDatabase(context).userDao())
//    }
//
//    override val userPreferencesRepository: UserPreferencesRepository by lazy {
//        UserPreferencesRepository(context.dataStore)
//
//    }
//}
