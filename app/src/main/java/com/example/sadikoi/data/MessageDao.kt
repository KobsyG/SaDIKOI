package com.example.sadikoi.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface MessageDao {

    @Insert //todo setup onConflict ?
    suspend fun insert(message: Message) //todo suspend devant fun pour le run on a separate thread //fun insertAll(vararg users: User) ?

    @Delete
    suspend fun delete(message: Message) //todo suspend devant fun pour le run on a separate thread

    @Query("SELECT * FROM message WHERE id = :id") //todo order probably
    fun getByContactId(id: Int): Flow<List<Message>>

    @Query("SELECT * FROM message WHERE id = :id ORDER BY timestamp DESC LIMIT 1")
    fun getLastMessageFromUser(id: Int): Flow<Message>

    //select last message from all user
    @Query("SELECT * FROM message WHERE (contactId, timestamp) IN (SELECT contactId, MAX(timestamp) FROM message GROUP BY contactId) ORDER BY timestamp DESC")
    fun getAllLastMessages(): Flow<List<Message>>

    //select the last message from all users
//    @Query("SELECT * FROM message WHERE id IN (SELECT id FROM message GROUP BY id ORDER BY MAX(timestamp) DESC) ORDER BY timestamp DESC LIMIT 1")
//    fun getAllLastMessages(): Flow<List<Message>>




}