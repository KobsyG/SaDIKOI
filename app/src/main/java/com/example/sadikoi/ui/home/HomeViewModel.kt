package com.example.sadikoi.ui.home

import android.util.Log
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sadikoi.data.IMessagesRepository
import com.example.sadikoi.data.IUsersRepository
import com.example.sadikoi.data.MessagesRepository
import com.example.sadikoi.data.UsersRepository
import com.example.sadikoi.ui.conversation.ConvPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class HomeViewModel(private val messagesRepository: IMessagesRepository, private val usersRepository: IUsersRepository) : ViewModel() {

//    private val _convPreviews = MutableStateFlow<List<ConversationPreview>>(emptyList())
//    val convPreviews: StateFlow<List<ConversationPreview>> = _convPreviews.asStateFlow()

    val convPreviews: StateFlow<List<ConversationPreview>> =
        messagesRepository.getAllLastMessages().map { message ->

            message.map { message ->

                usersRepository.getUserNameById(message.contactId).map { contactName ->

                    Log.d("HomeViewModel", "contactName: $contactName , messageNumber: ${message.number}")
                    ConversationPreview(
                        message.contactId,
//                        "michel",
                        if (contactName.isNullOrBlank()) message.number else contactName,
//                        contactName ?: message.number,
                        message.messageText
                    )
                }

            }
            }
            .map { previewsFlowList ->
                previewsFlowList.map { it.first() }
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = emptyList()
            )


}

data class ConversationPreview(
    val contactId: Int = -1,
    val contactName: String = "",
    val lastMessage: String = "",
)