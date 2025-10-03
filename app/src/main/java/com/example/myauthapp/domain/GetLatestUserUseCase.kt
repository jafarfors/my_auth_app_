package com.example.myauthapp.domain

import com.example.myauthapp.data.UserEntity
import com.example.myauthapp.repository.UserRepository
import kotlinx.coroutines.flow.Flow

class GetLatestUserUseCase(
    private val repository: UserRepository
) {
    operator fun invoke(): Flow<UserEntity?> {
        return repository.getLatestUser()
    }
}