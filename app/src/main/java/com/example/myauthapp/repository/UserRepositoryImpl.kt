package com.example.myauthapp.repository

import com.example.myauthapp.data.UserDao
import com.example.myauthapp.data.UserEntity
import kotlinx.coroutines.flow.Flow

class UserRepositoryImpl(
    private val userDao: UserDao
) : UserRepository {

    override suspend fun saveUser(email: String, password: String) {
        val user = UserEntity(email = email, password = password)
        userDao.insertUser(user)
    }

    override fun getLatestUser(): Flow<UserEntity?> {
        return userDao.getLatestUser()
    }
}