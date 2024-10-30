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

}