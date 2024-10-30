package com.example.sadikoi

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.provider.Telephony
import com.example.sadikoi.data.AppDatabase
import com.example.sadikoi.data.IUsersRepository
import com.example.sadikoi.data.Message
import com.example.sadikoi.data.UserPreferencesRepository
import com.example.sadikoi.data.UsersRepository
import com.example.sadikoi.ui.conversation.MessageUi

class SmsReceiver() : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        if (Telephony.Sms.Intents.SMS_RECEIVED_ACTION == intent.action) {
            val smsMessages = Telephony.Sms.Intents.getMessagesFromIntent(intent)
            val database = AppDatabase.getDatabase(context)
            for (message in smsMessages) {
                val messageBody = message.messageBody
                val sender = message.originatingAddress
//                val messageUi = Message(
//                    messageText = messageBody,
//                    contactId = database.userDao().getUser(sender),
//                    receiver = "me",
//                    timestamp = System.currentTimeMillis()
                )
//                database.messageDao().insert(message)
//                viewModel.onSmsReceived(messageUi) //todo getDatabase and use Dao to update DB so viewmodel can see update
                //todo see if i choose shared preference instead
            }
        }
    }
}

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
