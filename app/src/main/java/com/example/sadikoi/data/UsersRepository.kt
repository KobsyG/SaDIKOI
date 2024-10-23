package com.example.sadikoi.data

import kotlinx.coroutines.flow.Flow

interface IUsersRepository {

    fun getAllUsers(): Flow<List<User>>

    fun getUser(id: Int): Flow<User?>

    suspend fun insertUser(user: User)

    suspend fun deleteUser(user: User)

    suspend fun updateUser(user: User)
}


    class UsersRepository(private val userDao: UserDao) : IUsersRepository  { //todo extend this form an interface UsersRepository ou IUsersRepository ?

        override fun getAllUsers(): Flow<List<User>> = userDao.getAll()

        override fun getUser(id: Int): Flow<User?> = userDao.getUser(id)

        override suspend fun insertUser(user: User) = userDao.insert(user)

        override suspend fun deleteUser(user: User) = userDao.delete(user)

        override suspend fun updateUser(user: User) = userDao.update(user)
    }