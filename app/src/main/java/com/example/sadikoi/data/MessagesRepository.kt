package com.example.sadikoi.data

import kotlinx.coroutines.flow.Flow

interface IMessagesRepository {

    suspend fun insertMessage(message: Message)

    suspend fun deleteMessage(message: Message)

    fun getAllMessagesFromUser(id: Int): Flow<List<Message>>

    fun getLastMessageFromUser(id: Int): Flow<Message>

    fun getAllLastMessages(): Flow<List<Message>>
}

class MessagesRepository(private val messageDao: MessageDao) : IMessagesRepository {

    override suspend fun insertMessage(message: Message) = messageDao.insert(message)

    override suspend fun deleteMessage(message: Message) = messageDao.delete(message)

    override fun getAllMessagesFromUser(id: Int) = messageDao.getByContactId(id)

    override fun getLastMessageFromUser(id: Int) = messageDao.getLastMessageFromUser(id)

    override fun getAllLastMessages() = messageDao.getAllLastMessages()

}