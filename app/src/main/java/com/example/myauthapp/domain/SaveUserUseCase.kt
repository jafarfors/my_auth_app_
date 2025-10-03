package com.example.myauthapp.domain

import com.example.myauthapp.repository.UserRepository

class SaveUserUseCase(
    private val repository: UserRepository
) {
    suspend operator fun invoke(email: String, password: String) {
        repository.saveUser(email, password)
    }
}