package com.example.sadikoi.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow // librairie externe ???

@Dao
interface UserDao {
    @Insert //todo setup onConflict ?
    suspend fun insert(user: User) //todo suspend devant fun pour le run on a separate thread //fun insertAll(vararg users: User) ?

    @Delete
    suspend fun delete(user: User) //todo suspend devant fun pour le run on a separate thread

    @Update
    suspend fun update(user: User)

    @Query("SELECT * FROM users")
    fun getAll(): Flow<List<User>> //todo Flow???

    @Query("SELECT * from users WHERE id = :id")
    fun getUser(id: Int): Flow<User> //todo Flow???

    @Query("SELECT first_name, last_name FROM users")
    fun loadFullName(): List<NameTuple>
}

// Check Flow for asynchrone write observable queries