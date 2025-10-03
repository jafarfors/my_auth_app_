package com.example.myauthapp.repository

import com.example.myauthapp.data.UserEntity
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    suspend fun saveUser(email: String, password: String)
    fun getLatestUser(): Flow<UserEntity?>
}