package com.example.myauthapp.presentation

import com.example.myauthapp.data.UserEntity
import com.example.myauthapp.domain.GetLatestUserUseCase
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val getLatestUserUseCase: GetLatestUserUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(ProfileState())
    val uiState = _uiState.asStateFlow()

    fun loadUser() {
        viewModelScope.launch {
            getLatestUserUseCase().collect { user ->
                _uiState.value = _uiState.value.copy(user = user)
            }
        }
    }
}

data class ProfileState(
    val user: UserEntity? = null
)